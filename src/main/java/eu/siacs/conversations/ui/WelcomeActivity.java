package eu.siacs.conversations.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import eu.siacs.conversations.R;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		if (getResources().getBoolean(R.bool.portrait_only)) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			ActivityCompat.requestPermissions(WelcomeActivity.this,
					new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
					1);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		final Button createAccount = (Button) findViewById(R.id.create_account);
		createAccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WelcomeActivity.this, MagicCreateActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});
		final Button useOwnProvider = (Button) findViewById(R.id.use_own_provider);
		useOwnProvider.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(WelcomeActivity.this, EditAccountActivity.class));
			}
		});

	}

}
