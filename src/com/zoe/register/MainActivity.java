package com.zoe.register;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Context mContext;
	TextView loginTitle;
	TextView registerTitle;
	TextView resetPassword;

	DeletableEditText userNameInput;
	DeletableEditText userPasswordInput;
	DeletableEditText userEmailInput;
	Button registerButton;

	private enum UserOperation {
		LOGIN, REGISTER, RESET_PASSWORD
	}

	UserOperation operation = UserOperation.LOGIN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mContext = this;
		loginTitle = (TextView) findViewById(R.id.login_menu);
		registerTitle = (TextView) findViewById(R.id.register_menu);
		resetPassword = (TextView) findViewById(R.id.reset_password_menu);

		userNameInput = (DeletableEditText) findViewById(R.id.user_name_input);
		userPasswordInput = (DeletableEditText) findViewById(R.id.user_password_input);
		userEmailInput = (DeletableEditText) findViewById(R.id.user_email_input);

		registerButton = (Button) findViewById(R.id.register);

		loginTitle.setOnClickListener(this);
		registerTitle.setOnClickListener(this);
		resetPassword.setOnClickListener(this);
		registerButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_menu:
			operation = UserOperation.LOGIN;
			updateLayout(operation);
			break;
		case R.id.register_menu:
			operation = UserOperation.REGISTER;
			updateLayout(operation);
			break;
		case R.id.reset_password_menu:
			operation = UserOperation.RESET_PASSWORD;
			updateLayout(operation);
			break;
		case R.id.register:
			if (operation == UserOperation.LOGIN) {
				if (TextUtils.isEmpty(userNameInput.getText())) {
					userNameInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入用户名", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (TextUtils.isEmpty(userPasswordInput.getText())) {
					userPasswordInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				// 登入的逻辑代码
			} else if (operation == UserOperation.REGISTER) {
				if (TextUtils.isEmpty(userNameInput.getText())) {
					userNameInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入用户名", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (TextUtils.isEmpty(userPasswordInput.getText())) {
					userPasswordInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (TextUtils.isEmpty(userEmailInput.getText())) {
					userEmailInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入邮箱地址", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (!isValidEmail(userEmailInput.getText())) {
					userEmailInput.setShakeAnimation();
					Toast.makeText(mContext, "邮箱格式不正确", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				// 注册逻辑代码
			}else{
				if(TextUtils.isEmpty(userEmailInput.getText())){
					userEmailInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入邮箱地址", Toast.LENGTH_SHORT).show();
					return;
				}
				if(!isValidEmail(userEmailInput.getText())){
					userEmailInput.setShakeAnimation();
					Toast.makeText(mContext, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
					return;
				}
				// 逻辑代码
			}
			break;
		default:
			break;
		}
	}

	private void updateLayout(UserOperation op) {
		if (op == UserOperation.LOGIN) {
			loginTitle.setTextColor(Color.parseColor("#D95555"));
			loginTitle.setBackgroundResource(R.drawable.bg_login_tab);
			loginTitle.setPadding(16, 16, 16, 16);
			loginTitle.setGravity(Gravity.CENTER);

			registerTitle.setTextColor(Color.parseColor("#888888"));
			registerTitle.setBackgroundDrawable(null);
			registerTitle.setPadding(16, 16, 16, 16);
			registerTitle.setGravity(Gravity.CENTER);

			resetPassword.setTextColor(Color.parseColor("#888888"));
			resetPassword.setBackgroundDrawable(null);
			resetPassword.setPadding(16, 16, 16, 16);
			resetPassword.setGravity(Gravity.CENTER);

			userNameInput.setVisibility(View.VISIBLE);
			userPasswordInput.setVisibility(View.VISIBLE);
			userEmailInput.setVisibility(View.GONE);
			registerButton.setText("登录");
		} else if (op == UserOperation.REGISTER) {
			loginTitle.setTextColor(Color.parseColor("#888888"));
			loginTitle.setBackgroundDrawable(null);
			loginTitle.setPadding(16, 16, 16, 16);
			loginTitle.setGravity(Gravity.CENTER);

			registerTitle.setTextColor(Color.parseColor("#D95555"));
			registerTitle.setBackgroundResource(R.drawable.bg_login_tab);
			registerTitle.setPadding(16, 16, 16, 16);
			registerTitle.setGravity(Gravity.CENTER);

			resetPassword.setTextColor(Color.parseColor("#888888"));
			resetPassword.setBackgroundDrawable(null);
			resetPassword.setPadding(16, 16, 16, 16);
			resetPassword.setGravity(Gravity.CENTER);

			userNameInput.setVisibility(View.VISIBLE);
			userPasswordInput.setVisibility(View.VISIBLE);
			userEmailInput.setVisibility(View.VISIBLE);
			registerButton.setText("注册");
		} else {
			loginTitle.setTextColor(Color.parseColor("#888888"));
			loginTitle.setBackgroundDrawable(null);
			loginTitle.setPadding(16, 16, 16, 16);
			loginTitle.setGravity(Gravity.CENTER);

			registerTitle.setTextColor(Color.parseColor("#888888"));
			registerTitle.setBackgroundDrawable(null);
			registerTitle.setPadding(16, 16, 16, 16);
			registerTitle.setGravity(Gravity.CENTER);

			resetPassword.setTextColor(Color.parseColor("#D95555"));
			resetPassword.setBackgroundResource(R.drawable.bg_login_tab);
			resetPassword.setPadding(16, 16, 16, 16);
			resetPassword.setGravity(Gravity.CENTER);

			userNameInput.setVisibility(View.GONE);
			userPasswordInput.setVisibility(View.GONE);
			userEmailInput.setVisibility(View.VISIBLE);
			registerButton.setText("找回密码");
		}
	}

	// 判断是否符合邮箱格式
	public final static boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
					.matches();
		}
	}
}
