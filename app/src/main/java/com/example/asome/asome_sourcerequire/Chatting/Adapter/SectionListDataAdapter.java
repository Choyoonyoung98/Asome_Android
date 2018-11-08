package com.example.asome.asome_sourcerequire.Chatting.Adapter;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asome.asome_sourcerequire.Chatting.Etc.ChatUtils;
import com.example.asome.asome_sourcerequire.Chatting.Etc.DateFormat;
import com.example.asome.asome_sourcerequire.Chatting.Model.Chat;
import com.example.asome.asome_sourcerequire.Chatting.Model.SingleItemModel;
import com.example.asome.asome_sourcerequire.R;

import java.util.ArrayList;

import static com.example.asome.asome_sourcerequire.Chatting.Activity.ChatActivity.current_room_no;
import static com.example.asome.asome_sourcerequire.Chatting.Activity.ChatActivity.messages_adapter;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.Constant.ACTION_TEXT;
import static com.example.asome.asome_sourcerequire.Chatting.Etc.SocketClient.mWebSocketClient;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<SingleItemModel> itemsList;
    private Context mContext;

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_list_single, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        SingleItemModel singleItem = itemsList.get(i);

        holder.tvTitle.setText(singleItem.getName());
        holder.tvCode.setText(singleItem.getUrl());


       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle,tvCode;



        public SingleItemRowHolder(View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.tvCode= (TextView) view.findViewById(R.id.tvCode);
           // this.itemImage = (ImageView) view.findViewById(R.id.itemImage);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(v.getContext(), tvCode.getText(), Toast.LENGTH_SHORT).show();

                    Chat chat_mine = new Chat("11", current_room_no, DateFormat.date_apm(), tvTitle.getText().toString(), true, ACTION_TEXT);
                    mWebSocketClient.send(ChatUtils.chat_to_json_text(chat_mine));
                    Chat chat = new Chat("11", current_room_no, DateFormat.date_apm(), tvTitle.getText().toString(), true, tvCode.getText().toString());
                    mWebSocketClient.send(ChatUtils.chat_to_json_text(chat));
                    messages_adapter.notifyDataSetChanged();

                }
            });


        }

    }

}