package com.github.alvarosct.ascthelper.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.R;
import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;

import java.util.List;

public abstract class BaseSyncDatabaseFragment extends BaseFragment implements BaseSyncManager.ILoading {

    private ConstraintLayout constraintLayout;
    private ProgressBar progressBar;
    private TextView tvLoadingMessage;
    private TextView tvDownloadMessage;
    private TextView tvUploadMessage;
    private View ivUpload;
    private View ivDownload;

    private BaseSyncManager dbSync;

    private ConstraintSet constraintSet = new ConstraintSet();
    private ConstraintSet constraintSetDownload = new ConstraintSet();
    private ConstraintSet constraintSetUpload = new ConstraintSet();
    private boolean loading = false;

    public BaseSyncDatabaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateMenu(Menu menu) {

    }

    @Override
    public void setupVariables() {
        super.setupVariables();
        dbSync = getSyncManager();
    }

    @Override
    public void setupView(View view) {
        super.setupView(view);
        constraintSetDownload.clone(getContext(), R.layout.sync_database_download);
        constraintSetUpload.clone(getContext(), R.layout.sync_database_upload);
        constraintSet.clone(constraintLayout);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sync_database, container, false);

        constraintLayout = (ConstraintLayout) view.findViewById(R.id.constraint_layout);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        tvLoadingMessage = (TextView) view.findViewById(R.id.tv_loading_message);
        tvDownloadMessage = (TextView) view.findViewById(R.id.tv_download_message);
        tvUploadMessage = (TextView) view.findViewById(R.id.tv_upload_message);
        ivUpload = view.findViewById(R.id.iv_upload);
        ivDownload = view.findViewById(R.id.iv_download);

        progressBar.setIndeterminate(true);

        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDownloadStart();
            }
        });

        ivUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUploadStart();
            }
        });

        return view;
    }

    public void onDownloadStart() {
        loading = true;
        tvDownloadMessage.setText("Descargando");
        TransitionManager.beginDelayedTransition(constraintLayout);
        constraintSetDownload.applyTo(constraintLayout);

        startDownloading();
    }

    public void onUploadStart() {
        loading = true;
//        TODO: Complete a flawless implementation of this feature
//        progressBar.setBackgroundColor();
        tvUploadMessage.setText("Enviando");
        TransitionManager.beginDelayedTransition(constraintLayout);
        constraintSetUpload.applyTo(constraintLayout);

        startUploading();
    }


    //region ILoading METHODS (#REGION)

    @Override
    public void updateLoadingStatus(final float percentage, final String message) {
        progressBar.setProgress((int) (percentage * 100));
        tvLoadingMessage.setText(message);
    }

    @Override
    public void onDownloadSucceed() {
        progressBar.setIndeterminate(false);
        progressBar.setProgress(100);
        tvLoadingMessage.setText("");

        new DialogCustom(getContext(),
                "¡Atención!", "La descarga de tablas ha concluido con exito",
                new DialogCustom.ButtonBehaviour("Ok", new DialogCustom.IButton() {
                    @Override
                    public void onButtonClick() {
                        Log.e("ASCT", "Eeeeexito...");
                        saveToLocalDB();
                    }
                })).show();
    }

    @Override
    public void onDownloadFailed(List<String> errors) {
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
        tvLoadingMessage.setText("");

        String message = "No se pudo completar la descarga correctamente.";
        for (String error : errors) {
            message += ("\n" + error);
        }

        new DialogCustom(getContext(),
                "¡Error!", message,
                new DialogCustom.ButtonBehaviour("Reintentar", new DialogCustom.IButton() {
                    @Override
                    public void onButtonClick() {
                        Log.e("ASCT", "CAGASTE!...");
                    }
                })).show();
    }

    @Override
    public void onUploadSucceed() {

    }

    @Override
    public void onUploadFailed(List<String> errorList) {

    }
    //endregion

    public abstract void onSyncProcessed();

    public abstract BaseSyncManager getSyncManager();

    public abstract void saveToLocalDB();

    private void onLoadingStop() {
        if (loading) {
            loading = false;
            tvDownloadMessage.setText("Descargar");
            tvUploadMessage.setText("Enviar");
            tvLoadingMessage.setText("Start");
            TransitionManager.beginDelayedTransition(constraintLayout);
            constraintSet.applyTo(constraintLayout);
        } else {
            getActivity().finish();
        }
    }

//    Presenter Methods

    public void startDownloading() {
        dbSync.downloadAll();
    }

    public void startUploading() {
        dbSync.updaloadAll();
    }
}
