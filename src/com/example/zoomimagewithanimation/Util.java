package com.example.zoomimagewithanimation;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

/** 
 * @ClassName: Util 
 * @Description: 
 * @author Cloay Email:shangrody@gmail.com
 * @date 2014-1-17 下午05:44:59 
 * 
 **/
public class Util {
	public static void browerImage(Activity activity, final ImageView originImageV, final ImageView scaleImageV){
		int x = originImageV.getLeft();
		int y = originImageV.getTop();
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		
		AnimationSet animationSet = new AnimationSet(true);
		
		TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.ABSOLUTE, x,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, y,
                Animation.ABSOLUTE, scaleImageV.getY() - getStatusBarHeight(activity));
        translateAnimation.setDuration(1500);
        animationSet.addAnimation(translateAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, (float)width/originImageV.getWidth(), 1.0f,
        		(float)width/originImageV.getWidth(), 
        		Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0);
        scaleAnimation.setDuration(1500);
        animationSet.addAnimation(scaleAnimation);
        
        
        animationSet.setFillEnabled(true);
        animationSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				originImageV.setVisibility(View.INVISIBLE);
				scaleImageV.setVisibility(View.VISIBLE);
				originImageV.clearAnimation();
			}
		});
        
        originImageV.startAnimation(animationSet);
	}
	
	public static void closeImage(Activity activity, final ImageView originImageV, final ImageView scaleImageV){
		int originX = originImageV.getLeft();
		int originY = originImageV.getTop();
		int scaleX = scaleImageV.getLeft();
		int scaleY = scaleImageV.getTop();
		System.out.println("scaleY: " + scaleY + " bottom: " + scaleImageV.getBottom());
		
		AnimationSet animationSet = new AnimationSet(true);
		
		TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.ABSOLUTE, scaleX,
                Animation.ABSOLUTE, originX,
                Animation.ABSOLUTE, scaleY - getStatusBarHeight(activity),
                Animation.ABSOLUTE, originY);
        translateAnimation.setDuration(1500);
        animationSet.addAnimation(translateAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation((float)scaleImageV.getWidth()/originImageV.getWidth(), 1.0f, 
        		(float)scaleImageV.getWidth()/originImageV.getWidth(), 1.0f,  
        		Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0);
        scaleAnimation.setDuration(1500);
        animationSet.addAnimation(scaleAnimation);
        
        
        animationSet.setFillEnabled(true);
        animationSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				scaleImageV.setVisibility(View.INVISIBLE);
				originImageV.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				originImageV.clearAnimation();
			}
		});
        
        originImageV.startAnimation(animationSet);
	}
	

	public static Bitmap getBitmap(Activity context, int photoId){
		
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), photoId);
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		float scale = (float)width/bitmap.getWidth();
		Matrix matrix = new Matrix();
	   	matrix.postScale(scale, scale);
	   	// 得到新的图片
	   	Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	   	return Bitmap.createBitmap(newbm, 0, 0, (int)(bitmap.getWidth()*scale), (int)(bitmap.getHeight()*scale));
	}

	/**
	* @Title: getStatusBarHeight 
	* @Description:  
	* @param activity
	* @return int     
	 */
	public static int getStatusBarHeight(Activity activity){
		int result = 0;
		   int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
		   if (resourceId > 0) {
		      result = activity.getResources().getDimensionPixelSize(resourceId);
		   }
		   return result;
	}
}
