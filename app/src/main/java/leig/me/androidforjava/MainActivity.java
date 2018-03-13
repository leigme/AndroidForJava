package leig.me.androidforjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        initDialog();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private void initDialog() {
        final CustomDialog dialog = new CustomDialog(MainActivity.this);
        dialog.setMessage("对于一个在北平住惯的人，像我，冬天要是不刮风，便觉得是奇迹;济南的冬天是没有风声的。")
                .setImageResId(R.mipmap.ic_launcher)
//                .setTitle("系统提示")
                .setSingle(true).setOnClickBottomListener(new CustomDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "xxxx", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegtiveClick() {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "ssss", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
