package com.example.administrator.demo2;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareDialog;

public class MainActivity extends FragmentActivity {


    Button bt1,bt2;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {

            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });




        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        bt1=(Button)findViewById(R.id.button);
        bt2=(Button)findViewById(R.id.button2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khong thanh cong
                Toast.makeText(MainActivity.this,"fdsfds",Toast.LENGTH_SHORT).show();
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://www.google.com"))
                        .build();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Hello Facebook")
                            .setContentDescription(
                                    "The 'Hello Facebook' sample  showcases simple Facebook integration")
                            .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                            .build();

                    shareDialog.show(linkContent);
                }
            }
        });


        LikeView likeView = (LikeView) findViewById(R.id.likeview);
        likeView.setObjectIdAndType(
                "https://www.facebook.com/protechvn1",
                LikeView.ObjectType.PAGE);


    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
