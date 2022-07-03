package vn.trungkma.money.ui.dialog;

import static vn.trungkma.money.common.Constant.TOAST_RENAME_FAILED;
import static vn.trungkma.money.common.Constant.TOAST_RENAME_SUCCESS;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import vn.trungkma.money.R;
import vn.trungkma.money.common.Constant;
import vn.trungkma.money.common.Event;

public class DialogRename extends Dialog {

    private Context context;
    private String path;
    private String fileName;
    private EditText edtFileName;
    private Button btnRename;
    private ProgressBar progressRename;
    private TextView tvFileName;
    private Handler handler;

    public DialogRename(@NonNull Context context, String path, String fileName) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_rename);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        this.context = context;
        this.path = path;
        this.fileName = fileName;

        initDialog();

    }

    private void initDialog() {
        btnRename = findViewById(R.id.btn_reName);
        edtFileName = findViewById(R.id.edt_dialog_reName);
        progressRename = findViewById(R.id.progress_rename);
        tvFileName = findViewById(R.id.tv_reName_fileName);

        edtFileName.setText(fileName);
        edtFileName.setEnabled(true);
        // edtFileName.setSelection(edtFileName.getText().length() - 4);
        btnRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* File file = new File(path);
                File destFile = new File(file, edtFileName.getText().toString());
                if (file.renameTo(destFile) && destFile.exists()) {
                    dismiss();
                } else {

                }*/
                tvFileName.setVisibility(View.INVISIBLE);
                edtFileName.setVisibility(View.INVISIBLE);
                btnRename.setVisibility(View.INVISIBLE);
                progressRename.setVisibility(View.VISIBLE);

                File target = new File(path);
                File destFile = new File(target.getParent(), edtFileName.getText().toString());


                handler = new Handler(context.getMainLooper());
                handler.postDelayed(() -> {
                    progressRename.setVisibility(View.INVISIBLE);
                    if (target.renameTo(destFile) && destFile.exists()) {
                        Toast.makeText(context, TOAST_RENAME_SUCCESS, Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new Event(Constant.EVENT_CHANGE_NAME,destFile.getName(),destFile.getPath() ));
                        dismiss();
                    } else {
                        Toast.makeText(context, TOAST_RENAME_FAILED, Toast.LENGTH_SHORT).show();
                    }
                }, 500);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
