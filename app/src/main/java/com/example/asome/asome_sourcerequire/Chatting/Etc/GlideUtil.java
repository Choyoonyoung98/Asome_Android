package com.example.asome.asome_sourcerequire.Chatting.Etc;

/**
 * [OUTLINE]
 * 이미지 URL을 이미지 뷰로 보여주는 메소드를 담아 놓은 클래스
 *
 * [METHOD]
 *
 *setBitmapToView : 이미지 URL을 받아와서 이미지뷰에 넣어주는 메소드
 *setBitmapToViewRound : 사진 원형으로 나타내주는 메소드
 *setBitmapToViewRoundProfile : 프로필 사진 원형으로 나타내주는 메소드
 * Created by anfrh on 2017-06-21.
 */
    public class GlideUtil {/*


    //이미지 URL을 받아와서 이미지뷰에
    public static void setBitmapToView(String url, ImageView imageView) {

        if (TextUtils.isEmpty(url)||imageView==null) {
            return;
        } else {
            Glide.with(imageView.getContext())
                    .load(Uri.parse(url))//로드 할 URL
                    //.placeholder(R.drawable.ic_camera_alt_white_24dp)//로딩 시에
                    //error(R.drawable.ic_error_outline_black_48dp)//에러 났을 때
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView);//보여줄 이미지 뷰
        }
    }

    public static void setBitmapToViewRound(String url, ImageView imageView) {

        if (TextUtils.isEmpty(url)||imageView==null) {
            return;
        } else {
            Glide.with(imageView.getContext())
                    .load(Uri.parse(url))//로드 할 URL
               //     .placeholder(R.drawable.ic_camera_alt_white_24dp)//로딩 시에
               //     .bitmapTransform(new CropCircleTransformation(imageView.getContext()))//라운드로 크롭
             //       .error(R.drawable.testman)//에러 났을 때
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView);//보여줄 이미지 뷰
        }
    }
    public static void setBitmapToViewRoundProfile(String url, ImageView imageView) {

        if (TextUtils.isEmpty(url)||imageView==null) {
            return;
        } else {
            Log.d("ASD",url);
            if(url.equals("man")) {
                Glide.with(imageView.getContext())
                        .load(R.drawable.dialog_man)
                        .placeholder(R.drawable.dialog_man)
                        .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                        .error(R.drawable.dialog_man)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageView);
            }
            else
            {
                Glide.with(imageView.getContext())
                        .load(R.drawable.dialog_girl)
                        .placeholder(R.drawable.dialog_girl)
                        .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                        .error(R.drawable.dialog_girl)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageView);
            }
        }
    }

    public static void setBitmapToViewRoundGiljabee(String url, ImageView imageView) {

        if (TextUtils.isEmpty(url)||imageView==null) {
            return;
        } else {
            Glide.with(imageView.getContext())
                    .load(Uri.parse(url))
                    .placeholder(R.drawable.test_man)
                    .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                    .error(R.drawable.test_man)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView);
        }
    }
*/

}