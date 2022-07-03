package vn.trungkma.money.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import java.io.File;

import vn.trungkma.money.R;

public class DialogNewFolder extends Dialog {

    private EditText edtNewFolder;
    private Button btnOk;

    public DialogNewFolder(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_new_folder);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        initDialog();
    }

    private void initDialog() {
        edtNewFolder = findViewById(R.id.edt_newFolder_name);
        btnOk = findViewById(R.id.btn_newFolder);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Zazip/Extracted");
        directory.mkdirs();
        File directory1 = new File(Environment.getExternalStorageDirectory() + File.separator + "Zazip/Compressed");
        directory1.mkdirs();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
