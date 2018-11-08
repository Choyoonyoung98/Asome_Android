package com.example.asome.asome_sourcerequire.Chatting.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asome.asome_sourcerequire.Chatting.Adapter.ChatMessageAdapter;
import com.example.asome.asome_sourcerequire.Chatting.Etc.ChatUtils;
import com.example.asome.asome_sourcerequire.Chatting.Etc.Constant;
import com.example.asome.asome_sourcerequire.Chatting.Etc.DateFormat;
import com.example.asome.asome_sourcerequire.Chatting.Etc.SocketClient;
import com.example.asome.asome_sourcerequire.Chatting.Fragment.BottomSheetDialog;
import com.example.asome.asome_sourcerequire.Chatting.Model.Chat;
import com.example.asome.asome_sourcerequire.Project.Role;
import com.example.asome.asome_sourcerequire.R;
import com.example.asome.asome_sourcerequire.Utils.HTTP.RoleSelect;
import com.example.asome.asome_sourcerequire.Utils.SQLite.DBHelperChatting;

import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_CALL;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_DONE;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_SCHEDULE_MY;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_SCHEDULE_OTHER;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_START;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_TEXT;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.SELECT_ROLE;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.SocketClient.mWebSocketClient;


/**
 * [OUTLINE]
 * HorizontalActivity에서 채팅 방을 클릭하면 넘어오는 채팅방 화면이다
 * <p>
 * [METHOD]
 * (override 함수 생략)
 * void image_send_process(Uri): 이미지를 상대에게 전송할 때 프로세스를 담은 함수
 * void insert_date_lIne(): 날짜 구분선 그어주는 함수
 * byte[] bitmap_to_byte(Bitmap): 선택된 이미지의 비트맵을 받아 바이트 데이터를 리턴 하는 함수
 * void chat_receiver(): 이 액티비티가 살아있을 동안 소켓에서 오는 데이터들을 실시간으로 받아주는 리시버 함수
 * void scroll_to_bottom(): 리스트뷰 맨 아래로 내려주는 함수
 * void gps_track(): 사용자의 현재 위치 파악해주는 함수
 * void init_layout(),init_system(): 화면 생성시 초기화 관련 프로세스 실행하는 함수
 * void back_press(): 뒤로가기 액션시 수행될 프로세스를 담은 함수
 * void check_permissions(): 사용자에게 퍼미션 체크 요구하는 함수
 * <p>
 * [FLOW]
 * 1. onCreate
 * -init 함수로 초기화
 * -컴포넌트의 액션 이벤트 설정
 * -내부 외부 뱃지 = 0 이벤트
 * 2. User Control
 * -메시지 수신/발신 (텍스트,이미지)
 * -상단 액션바의 맵사진을 누르면 맵 화면으로 넘어간다
 * 3. onStop
 * - temp_disconnected 패킷을 보내 현 사용자가 채팅액티비티에 없음을 알린다
 * 4. onResume, onRestart
 * -restart 패킷을 보내 현 사용자가 채팅액티비티에 다시 들어왔음을 알린다
 * 5. onDestroy
 * - temp_disconnected 패킷을 보내 현 사용자가 채팅액티비티에 없음을 알린다
 * <p>
 * [LIBRARY]
 * 1. java_websocket: 안드로이드 웹소켓 클라이언트 라이브러리
 * 2. recentimages: 최근 이미지 리스트를 하단에 뿌려주기 위한 라이브러리
 * 3. shortcutbadger: 외부 뱃지 간단히 처리하기 위한 라이브러리
 */

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "[ChatActivity]";

    //정적 변수
    public static ArrayList<Chat> chats = new ArrayList<Chat>();//메시지 리스트
    public static  ArrayList<Role> roles = new ArrayList<>();

    public static ChatMessageAdapter messages_adapter = new ChatMessageAdapter(chats);//메시지 리스트 어댑터
    public static String current_name="11";//현재방에서 내아이디
    public static String current_room_no;//현재방 아이디
    public static String current_counter_name;//현재방에서 상대방 아이디

    ChatUtils chat_utils = new ChatUtils(ChatActivity.this);//받은 채팅 메시지(json)를 message 구조체에 맞게 바꾸어 주는 유틸
    DBHelperChatting db_helper_chat = new DBHelperChatting(ChatActivity.this, "CHAT.db", null, 1);//받은 메시지 저장 또는 가져오기


    //그 외
    private BroadcastReceiver chat_receiver;//채팅 페이지가 켜져 있을 때 실시간으로 패킷을 받는 내부 리시버
    private double latitude, longitude;//현재 위치 잡은 위도 경도 받는 변수

    //화면 관련 변수들
    Button btn_send_message;//채팅 보내는 버튼
    Button btn_show_image;//이미지 선택창 올리는 버튼
    Dialog dialog_bottom_sheet;//하단 최근 이미지
    EditText edit_message;//채팅방에서 채팅쓰는 에딧텍스트
    RecyclerView rv_image_menu;//바텀시트에서 "갤러리"라고 보여줄 뷰 (차후에 음악/동영상/..을 추가할 예정이었음)
    RecyclerView rv_chat_message;//채팅내용 리스트뷰
    View bottom_sheet;//하단 최근 이미지 리스트 보여줄 뷰
    //  TwoWayGridView img_list_grid_view;//하단 최근 이미지 그리드뷰 (**라이브러리)


    /**
     * [onCreate()]
     * 1. 초기화
     * 2. 액션 이벤트
     * 3. 뱃지처리
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        /**
         * [1] 초기화 메스드는 2가지
         *1.init_layout: 레이아웃 관련 초기화 프로세스를 구현한 메소드이다
         *2.init_system: 그 외 인텐트나 화면이 최초로 생성 될 때 수행되어야할 프로세스들을 구현한 메소드이다
         * */
        init_layout();
        init_system();
        SocketClient sc = new SocketClient(getApplicationContext());
        sc.connectWebSocket(current_room_no);
        /**
         * [2] 레이아웃 아이템 액션 4가지
         * 1. rv_image_menu.addOnItemTouchListener: 이미지 보낼때 이미지 선택창 올리는 액션
         * 2. btn_send_message.setOnClickListener: 메시지 보내기 액션
         * 3. btn_show_image.setOnClickListener:
         화면 하단에 +모양 버튼을 누르면 나타나는 이벤트
         화면 하단에 1XN 형식의 이미지 그리드뷰가 생성된다
         이는 최근 접근 했던 이미지리스트이며 이 이미지 중 하나를 클릭하면 해당 이미지를 채팅에 전송한다.-->image_send_process() 메소드가 이를 수행한다
         * 4. edit_message.setOnClickListener: 채팅 에딧텍스트 눌렀을때 리스트 맨 아래로 내리는 액션
         * */

        Chat chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "안녕하세요. 무엇을 도와드릴까요?  ", false, ACTION_START);
        chats.add(chat);

        //메시지 보내기 액션
        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       Chat chat;
                //메시지 내용이 있을때만 보내도록 처리
                if (!TextUtils.isEmpty(getMessage())) {


                    try {

                        //해당 메시지 구조를 완성하고 메시지를 전송한다
                        chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), getMessage(), true, ACTION_TEXT);
                        mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                       // chats.add(chat);


                        if (getMessage().contains(ACTION_START)) {
                            chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "무엇을 도와드릴까요?? < 메뉴 선택 창 >  ", false, ACTION_START);
                            mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                            chats.add(chat);
                        }

                        if (getMessage().contains(ACTION_SCHEDULE_MY)) {
                            chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "당신 오늘 스케줄은 ~~~이다.", false, ACTION_TEXT);
                            mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                            chats.add(chat);
                        }
                        if (getMessage().contains(ACTION_SCHEDULE_OTHER)) {
                            chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "누구 스케쥴 보시겠습니까?<Listview>", false, ACTION_SCHEDULE_OTHER);
                            mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                            chats.add(chat);
                        }
                        if (getMessage().contains(ACTION_CALL)) {
                            chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "어떤 호출하시겠습니까?<응급,비상,...>", false, ACTION_TEXT);
                            mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                            chats.add(chat);
                        }
                        if (getMessage().contains(ACTION_DONE)) {
                            chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "오늘 일정 완료하시겠습니까?", false, ACTION_TEXT);
                            mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                            chats.add(chat);
                        }
                        if (getMessage().contains("bye")) {
                            chat = new Chat(current_name, current_room_no, DateFormat.date_apm(), "잘가요~~", false, ACTION_TEXT);
                            mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                            chats.add(chat);
                        }


                        //네트워크 연결이 끊겼을때는 에러처리 스낵바를 띄운다
                    } catch (Exception e) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        Snackbar.make(getWindow().getDecorView().getRootView(), "네트워크 연결상태를 확인해주세요", Snackbar.LENGTH_LONG).setAction("닫기", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
                    }

                    //메시지를 다보낸 후에는 리스트 맨아래로 보내고 에딧텍스트는 빈칸으로 처리한다
                    scroll_to_bottom();
                    edit_message.setText("");

                }

            }
        });

        // 화면 하단에 +모양 버튼을 누르면 나타나는 이벤트
        // 화면 하단에 1XN 형식의 이미지 그리드뷰가 생성된다
        // 이는 최근 접근 했던 이미지리스트이며 이 이미지 중 하나를 클릭하면 해당 이미지를 채팅에 전송한다.-->image_send_process() 메소드가 이를 수행한다
        btn_show_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.getInstance();
                bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");


/*                //이미지 관련 퍼미션 체크
                check_permissions();

                //(**라이브러리) 최근 이미지 리스트를 불러와 하단뷰에 배치
                RecentImages ri = new RecentImages();
                ImageAdapter adapter = ri.getAdapter(ChatActivity.this);
                img_list_grid_view.setAdapter(adapter);

                //하단의 이미지 리스트에서 이미지 하나를 골라 보낸다
                img_list_grid_view.setOnItemClickListener(new TwoWayAdapterView.OnItemClickListener() {
                    public void onItemClick(TwoWayAdapterView parent, View v, int position, long id) {
                        dialog_bottom_sheet.dismiss();
                        Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

                        //이미지 전송 프로세스

                    }
                });*/
            }
        });


        //채팅화면을 작성하기 위해 에딧 텍스트를 클릭하면 리스트뷰 맨 아래로 재조정해준다.
        edit_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroll_to_bottom();
            }
        });

        /**[3] 뱃지 이벤트: 안읽은 메시지를 세어주는 내/외부 메시지를 0으로 변경시켜준다.*/
        //ShortcutBadger.applyCount(getApplicationContext(), db_helper_chat.get_outer_badge_num(current_room_no));//(**라이브러리)
        db_helper_chat.update_badge_to_zero(current_room_no);

    }


    /**
     * 메소드 [2]
     * 날짜 구분선 그어주는 함수
     */
    private void insert_date_lIne() {
        if (!DateFormat.date_month_and_day().equals(db_helper_chat.get_last_date(current_room_no))) {
            Chat chat = new Chat(current_name, current_room_no, DateFormat.date_month_day_time(), "---- " + DateFormat.date_month() + "월 " + DateFormat.date_day() + "일 ---- ", true, "dateLine");
            db_helper_chat.insert_chat(current_name, current_room_no, "---- " + DateFormat.date_month() + "월 " + DateFormat.date_day() + "일 ---- ", "read", DateFormat.date_month_day_time(), "true", "dateLine", 0, 0);
            chats.add(chat);
        }
    }


    //갤러리 들어갈 때
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            switch (requestCode) {
              /*  case SELECT_PHOTO:
                    if (resultCode == Activity.RESULT_OK) {

                    }
                    break;*/
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "사진 선택 취소", Toast.LENGTH_LONG).show();
        }

    }


    /**
     * 메소드 [4]
     * 이 액티비티가 살아있을 동안 소켓에서 오는 데이터들을 실시간으로 받아주는 리시버 함수
     * 웹소켓 클라이언트에서는 서버에서 유저에게 오는 메시지를 받아 response 라는 방송을 한다
     * ChatActivity의 리시버는 그 방송을 받아서 메시지창에 실시간으로 보여준다
     */
    private void chatActivityReceiver() {

        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("Response");
        chat_receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String json = intent.getStringExtra("response");
                Log.i(TAG, json);
                try {
                    Chat msg = chat_utils.json_to_chat_front(json);//서버에서 받은 json형식의 메시지를 Message구조체로 바꾼다
                    if (msg != null) {
                        chats.add(msg);//메시지 리스트에 추가
                        scroll_to_bottom();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        registerReceiver(chat_receiver, intentfilter);
    }

    /**
     * 메소드 [5]
     * 리스트뷰 맨 아래로 내려주는 함수
     */
    private void scroll_to_bottom() {
        rv_chat_message.scrollToPosition(chats.size() - 1);
    }

    //에딧 텍스트의 문자열 리턴
    private String getMessage() {
        return edit_message.getText().toString().trim();
    }

    //메뉴바
    //메시지 내용을 서치할 수 있는 서치바
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    /*    getMenuInflater().inflate(R.menu.menu_chat, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                messages_adapter.filter(newText);
                return false;
            }
        });*/
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            back_press();
        }
/*
        //내상태를 가이드에게 보낼 수 있게 맵 액티비티를 띄어주는 버튼
        //상단 맵메뉴 클릭시 SendMapActivity 넘어가는데 위도,경도를 parameter 로 넘긴다
        if (id == R.id.menu_map) {
            Intent i = new Intent(getApplicationContext(), SendMapActivity.class);
            i.putExtra("lat", latitude);
            i.putExtra("lng", longitude);
            startActivity(i);
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }


    /**
     * 초기 셋팅
     * 화면 초진입시 레이아웃 관련 필요한 설정들 모아놓은 함수입니다
     */
    private void init_layout() {

        //액션바
/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //컴포넌트
        edit_message = (EditText) findViewById(R.id.edit_message);
        btn_send_message = (Button) findViewById(R.id.btn_send_message);
        btn_show_image = (Button) findViewById(R.id.btn_image);
        rv_chat_message = (RecyclerView) findViewById(R.id.rv_chat_message);


/*

        //하단에 최근 이미지 목록을 보여주는 그리드뷰 메뉴
        bottom_sheet = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        img_list_grid_view = (TwoWayGridView) bottom_sheet.findViewById(R.id.gridview);
        dialog_bottom_sheet = new Dialog(this, R.style.MaterialDialogSheet);
        dialog_bottom_sheet.setContentView(bottom_sheet);
        dialog_bottom_sheet.setCancelable(true);
        dialog_bottom_sheet.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog_bottom_sheet.getWindow().setGravity(Gravity.BOTTOM);

        ArrayList<com.nova.giljabee.Chatting.Model.MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new com.nova.giljabee.Chatting.Model.MenuItem("갤러리", R.drawable.ic_action_image));
        menuItems.add(new com.nova.giljabee.Chatting.Model.MenuItem("선물보내기", R.drawable.ic_action_image));
        menuItems.add(new com.nova.giljabee.Chatting.Model.MenuItem("카메라", R.drawable.ic_action_image));
        menuItems.add(new com.nova.giljabee.Chatting.Model.MenuItem("지도", R.drawable.ic_action_image));
        menuItems.add(new com.nova.giljabee.Chatting.Model.MenuItem("선물보내기", R.drawable.ic_action_image));
        rv_image_menu = (RecyclerView) bottom_sheet.findViewById(R.id.menu);
        rv_image_menu.setLayoutManager(new LinearLayoutManager(this));
        rv_image_menu.setAdapter(new MenuAdapter(menuItems));
*/


        //test();

        //메인뷰
        rv_chat_message.setLayoutManager(new LinearLayoutManager(this));
        rv_chat_message.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.HORIZONTAL));
        rv_chat_message.setAdapter(new ChatMessageAdapter(chats));

    }

    //내방 관련 노티 오프
    //현 위치 잡기-->TODO: 맴 쪽으로 옯긴다
    //리시버 온
    //구성요소
    // {어레이리스트 객체, }

    /**
     * 초기 셋팅
     * 화면 초진입시 레이아웃 외 설정해야할 부분 관련 필요한 설정들 모아놓은 함수입니다
     */
    private void init_system() {

        //겟 인텐트 액션: 내이름, 상대 이름, 방이름
        //current_name = getIntent().getStringExtra(Constant.TAG_USER_NO);
        current_room_no = getIntent().getStringExtra(Constant.TAG_ROOM_NO);
        current_counter_name = getIntent().getStringExtra("guideName");


        RoleSelect roleSelect = new RoleSelect(current_room_no);
        roles.clear();
        try {
            roleSelect.execute().get();//current teammate?
            Toast.makeText(getApplicationContext(),roles.get(1).getRole_start_date(),Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //들어와있는 방의 fcm notification을 끈다(fcm 관련 작업 미완 --> 제거)
        // spf_notification.notiOff(current_room_no);

        //현위치 트래킹 함수 시작


        //채팅 메시지를 실시간으로 받는 리시버 켠다
        chatActivityReceiver();

        //해당 방의 모든 메시지 히스트로를 디비로부터 가져온다
        chats.clear();
        db_helper_chat.get_chat_history(current_room_no);

        //날짜선긋기
        insert_date_lIne();

        //액션이 {open}인 패킷을 서버로 보낸다
        try {
            mWebSocketClient.send(ChatUtils.chat_to_json_connected(new Chat(current_name, current_room_no)));
        } catch (WebsocketNotConnectedException e) {
            //    Toast.makeText(getApplicationContext(), "네트워크 오류입니다", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            //  Toast.makeText(getApplicationContext(), "오류입니다", Toast.LENGTH_LONG).show();
        }

        //툴바 헤더 이름을 현재 상대방이름이로 정한다
        setTitle(current_counter_name);
        scroll_to_bottom();

    }


    //READ_EXTERNAL_STORAGE 퍼미션 체크 (앨범열때)
    private void check_permissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    //퍼미션 결과
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "퍼미션완료",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "퍼미션 오류입니다",
                            Toast.LENGTH_SHORT).show();
                    check_permissions();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 생명 주기 관련 함수
     */

    //화면이 사라질때
    private void back_press() {
        db_helper_chat.update_badge_to_zero(current_room_no);//해당 방 뱃지를 0으로 만든다
        db_helper_chat.get_inner_badge_count(current_room_no);//그외 뱃지 카운트를 재설정한다-->방이 여러개 있을때 현재 방만 읽음처리하고 나머지 방은 안읽음 처리-->내부 외부 뱃지 리프레쉬
        try {
            mWebSocketClient.send(ChatUtils.chat_to_json_temp_disconnected(current_room_no));
        } catch (Exception e) {
        }
        db_helper_chat.lastDataChatToRoom();//현재방에서 나눈 마지막 줄 메시지를 방목록에서 보기위해 넘긴다
        //spf_notification.notiOn();//다시 이방의 fcm 노티피케이션을 켠다
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");

        db_helper_chat.lastDataChatToRoom();//현재방에서 나눈 마지막 줄 메시지를 방목록에서 보기위해 넘긴다

        try {
            mWebSocketClient.send(ChatUtils.chat_to_json_reconnect(new Chat(current_name, current_room_no)));//재접속 했음을 서버에 알린다
        } catch (WebsocketNotConnectedException e) {
            //    Toast.makeText(getApplicationContext(), "network error", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            //  Toast.makeText(getApplicationContext(), "other error", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
        db_helper_chat.lastDataChatToRoom();//현재방에서 나눈 마지막 줄 메시지를 방목록에서 보기위해 넘긴다
        try {
            mWebSocketClient.send(ChatUtils.chat_to_json_reconnect(new Chat(current_name, current_room_no)));//재접속 했음을 서버에 알린다
        } catch (WebsocketNotConnectedException e) {
            //     Toast.makeText(getApplicationContext(), "network error", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            //   Toast.makeText(getApplicationContext(), "other error", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        unregisterReceiver(chat_receiver);
        //spf_notification.notiOn();
        back_press();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        try {
            mWebSocketClient.send(ChatUtils.chat_to_json_temp_disconnected(current_room_no));//메시지를 실시간으로 확인하지 못함(temp_disconnected)을 서버에 알린다.
        } catch (Exception e) {

        }
        db_helper_chat.update_badge_to_zero(current_room_no);//이 방의 메시지를 모두 읽었으니 뱃지는 0
        db_helper_chat.lastDataChatToRoom();//현재방에서 나눈 마지막 줄 메시지를 방목록에서 보기위해 넘긴다
        //spf_notification.notiOn();
        // ShortcutBadger.applyCount(getApplicationContext(), db_helper_chat.get_outer_badge_num(current_room_no));//바깥뱃지 업데이트
    }


    //////////////////////////////////////////////


}



