package vn.trungkma.money.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import vn.trungkma.money.R;
import vn.trungkma.money.common.Constant;
import vn.trungkma.money.common.Event;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.utils.Utils;

public class DialogDelete extends Dialog {

    public DialogDelete.OnItemClickedListener onItemClickedListener;
    private Context context;
    private List<FileData> list = new LinkedList<>();
    private TextView tvFilename;
    private TextView tvRequest;
    private Button btnYes;
    private Button btnNo;
    private Handler handler;
    private ProgressBar progressDelete;

    private String fileName;

    public DialogDelete(@NonNull Context context, List<FileData> list) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_delete);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        this.context = context;
        this.list = list;

        initDialog();

    }

    private void initDialog() {
        tvFilename = findViewById(R.id.tv_delete_fileName);
        tvRequest = findViewById(R.id.tv_delete_request);
        btnYes = findViewById(R.id.btn_delete_yes);
        btnNo = findViewById(R.id.btn_delete_no);
        progressDelete = findViewById(R.id.progress_delete);

        if (list.size() == 1) {
            fileName = list.get(0).getFileName();
            tvFilename.setText(fileName + " ?");
        } else if (list.size() > 1) {
            fileName = String.valueOf(list.size() + " items");
            tvFilename.setText(fileName + " ?");
        }

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickedListener.onYesClick(v);

                tvFilename.setVisibility(View.INVISIBLE);
                tvRequest.setVisibility(View.INVISIBLE);
                btnNo.setVisibility(View.INVISIBLE);
                btnYes.setVisibility(View.INVISIBLE);
                progressDelete.setVisibility(View.VISIBLE);
                for (int i = 0; i < list.size(); i++) {
                    File file = new File(String.valueOf(list.get(0).getFilePath()));
                    Utils.deleteDirectory(file);
                  /*  Uri root = Uri.parse(list.get(i).getFilePath());

                    File file = new File(String.valueOf(root));
                    if (file.exists()) {
                        file.delete();
                    }*/
                    EventBus.getDefault().post(new Event(Constant.EVENT_DELETE, fileName, list.get(i).getFilePath()));
                }
                handler = new Handler(context.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDelete.setVisibility(View.INVISIBLE);

                        Toast.makeText(context, "Delete " + fileName + " success", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                }, 1000);
            }
        });

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
        void onYesClick(View view);

    }
}
