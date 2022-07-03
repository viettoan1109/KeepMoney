package vn.trungkma.money.ui.dialog;

import static vn.trungkma.money.common.Constant.LINK_DEFAUL_EXTRACT;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import vn.trungkma.money.R;
import vn.trungkma.money.data.model.FileData;

public class DialogExtract extends Dialog {

    private List<FileData> list = new LinkedList<>();
    private Context context;
    private String path;
    private EditText edtFileName;
    private TextView tvLink;
    private Button btnOk;

    public DialogExtract(@NonNull Context context, List<FileData> list) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_extract);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        this.context = context;
        this.list = list;
        initDialog();
    }

    private void initDialog() {
        edtFileName = findViewById(R.id.edt_extract_fileName);
        tvLink = findViewById(R.id.edt_extract_folder);
        btnOk = findViewById(R.id.btn_reName);

        tvLink.setText(LINK_DEFAUL_EXTRACT);
        edtFileName.setText(list.get(0).getFileName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


    }
}
