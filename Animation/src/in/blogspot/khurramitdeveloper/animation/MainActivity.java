package in.blogspot.khurramitdeveloper.animation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	ViewFlipper viewFlipper;
	Animation slide_in_left, slide_out_right;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = MainActivity.this;
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		for (int i = 0; i < 10; i++) {
			setFlipperImage("Subhanallah " + i);
		}

		slide_in_left = AnimationUtils.loadAnimation(this, R.drawable.left_in);
		slide_out_right = AnimationUtils.loadAnimation(this,
				R.drawable.left_out);
		viewFlipper.setInAnimation(slide_in_left);
		viewFlipper.setOutAnimation(slide_out_right);

		viewFlipper.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

	}

	private void setFlipperImage(final String str) {
		Log.e("Set Filpper Called", str + "");
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.flipper_item_view, null);
		TextView textView1 = (TextView) view.findViewById(R.id.textView1);
		TextView textView2 = (TextView) view.findViewById(R.id.textView2);
		textView1.setText("" + str);
		textView2.setText("" + str);
		Button button = (Button) view.findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
				new AlertDialog.Builder(context)

				.setMessage(str).show().setOnDismissListener(new OnDismissListener() {
					
					@Override
					public void onDismiss(DialogInterface dialog) {
						viewFlipper.startFlipping();
	
					}
				});
				viewFlipper.stopFlipping();

			}
		});
		viewFlipper.addView(view);
	}

	@Override
	protected void onPause() {
		Log.e(getClass().getSimpleName().toString(), "OnPause");
		super.onPause();
	}

	@Override
	protected void onResume() {
		viewFlipper.setAutoStart(true);
		viewFlipper.setFlipInterval(2000);
		viewFlipper.startFlipping();
		super.onResume();
	}
}
