package vn.trungkma.money.ui.dialog;

import static vn.trungkma.money.common.Constant.LINK_DEFAUL_COMPRESS;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import vn.trungkma.money.R;
import vn.trungkma.money.data.model.FileData;

public class DialogCompress extends Dialog {
    public DialogCompress.OnItemClickedListener onItemClickedListener;
    private Context context;

    private ToggleButton switchOn;
    private ToggleButton switchOff;
    private EditText edtPassword;
    private EditText edtFileName;
    private TextView tvLink;
    private ImageView imgShowPass;
    private ImageView imgHidePass;
    private List<FileData> list = new LinkedList<>();

    public DialogCompress(@NonNull Context context, List<FileData> list) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_compress);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        this.context = context;
        this.list = list;

        initDialog();
    }

    private void initDialog() {
        switchOff = findViewById(R.id.switch_compress_false);
        switchOn = findViewById(R.id.switch_compress_true);
        edtPassword = findViewById(R.id.edt_compress_password);
        edtFileName = findViewById(R.id.edt_compress_fileName);
        tvLink = findViewById(R.id.edt_compress_folder);
        imgShowPass = findViewById(R.id.img_compress_showPass);
        imgHidePass = findViewById(R.id.img_compress_hidePass);


        imgShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                imgHidePass.setVisibility(View.VISIBLE);
                imgShowPass.setVisibility(View.INVISIBLE);
                edtPassword.setSelection(edtPassword.getText().length());
            }
        });

        imgHidePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPassword.setInputType(129);
                imgHidePass.setVisibility(View.INVISIBLE);
                imgShowPass.setVisibility(View.VISIBLE);
                edtPassword.setSelection(edtPassword.getText().length());
            }
        });


        if (list.size() > 0) {
            edtFileName.setText(list.get(0).getFileName());
        }
        tvLink.setText(LINK_DEFAUL_COMPRESS);

        switchOff.setChecked(false);
        switchOn.setChecked(true);

        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickedListener.onClick(v);
            }
        });


        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.switch_compress_false:
                        edtPassword.setEnabled(true);
                        switchOn.setVisibility(View.VISIBLE);
                        switchOff.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.switch_compress_true:
                        edtPassword.setEnabled(false);
                        edtPassword.setText("");
                        switchOn.setVisibility(View.INVISIBLE);
                        switchOff.setVisibility(View.VISIBLE);
                        break;
                }
            }
        };

        switchOn.setOnCheckedChangeListener(listener);
        switchOff.setOnCheckedChangeListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void onClick(View view);

    }
}
