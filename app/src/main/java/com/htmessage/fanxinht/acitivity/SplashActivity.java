package com.htmessage.fanxinht.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import com.htmessage.fanxinht.R;
import com.htmessage.sdk.client.HTClient;
import com.htmessage.fanxinht.acitivity.login.LoginActivity;
import com.htmessage.fanxinht.acitivity.main.MainActivity;
import com.htmessage.fanxinht.utils.UpdateLocalLoginTimeUtils;

/**
 * 开屏页
 *
 */
public class SplashActivity extends  Activity {
    //继承Activity是为动画启动前无白屏卡顿
	@Override
	protected void onCreate(Bundle arg0) {
 		super.onCreate(arg0);
		setContentView(R.layout.activity_splash);
		RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.splash_root);
		AlphaAnimation animation = new AlphaAnimation(0.5f, 1.0f);
		animation.setDuration(2000);
		rootLayout.startAnimation(animation);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if (HTClient.getInstance().isLogined()) {
					//上传最近登录时间
					UpdateLocalLoginTimeUtils.sendLocalTimeToService(SplashActivity.this);
			    	Intent intent=new Intent(SplashActivity.this, MainActivity.class);
 					startActivity(intent);
					finish();
				}else {
					startActivity(new Intent(SplashActivity.this, LoginActivity.class));
					finish();
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
	}
}
