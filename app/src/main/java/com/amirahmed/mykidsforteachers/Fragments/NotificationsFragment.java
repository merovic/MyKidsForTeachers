package com.amirahmed.mykidsforteachers.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;

public class NotificationsFragment extends Fragment {

    LinearLayout layout,contentlayout;

    TextView textView,textView2;

    TextInputLayout textInputLayout;

    EditText content;

    Button button;

    TinyDB tinyDB;

    int language;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tinyDB = new TinyDB(getContext());

        language = tinyDB.getInt("language");

        layout = getActivity().findViewById(R.id.notificationtitle);
        contentlayout = getActivity().findViewById(R.id.contentlayout);

        textView = getActivity().findViewById(R.id.subjectdatenoti);
        textView2 = getActivity().findViewById(R.id.contect);

        textInputLayout = getActivity().findViewById(R.id.text);

        content = getActivity().findViewById(R.id.contectedit);

        button = getActivity().findViewById(R.id.button);

        if(language==1)
        {
            layout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            contentlayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            textView.setGravity(Gravity.RIGHT);
            textInputLayout.setHint("عنوان الاشعار *");
            textView2.setText("تفاصيل الاشعار *");

            button.setText("نشر الاشعار");

        }else
            {
                layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                contentlayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                textView.setGravity(Gravity.LEFT);
                textInputLayout.setHint("Notification Title *");
                textView2.setText("Notification Details *");


                button.setText("Publish Notification");
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(language==1)
                    {
                        showMessage("تم نشر الاشعار بنجاح");
                    }else
                        {
                            showMessage("Notification Published Successfully");
                        }

                        textView.setText("");
                    content.getText().clear();

                }
            });

    }

    private void showMessage(String _s) {
        Toast.makeText(getContext(), _s, Toast.LENGTH_SHORT).show();
    }
}
