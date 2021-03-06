package com.newsblur.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.newsblur.R;
import com.newsblur.network.APIManager;
import com.newsblur.util.FeedUtils;

public class SaveSearchFragment extends DialogFragment {

    private static final String FEED_ID = "feed_id";
    private static final String QUERY = "query";

    public static SaveSearchFragment newInstance(String feedId, String query) {
        SaveSearchFragment frag = new SaveSearchFragment();
        Bundle args = new Bundle();
        args.putString(FEED_ID, feedId);
        args.putString(QUERY, query);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(String.format(getResources().getString(R.string.add_saved_search_message), getArguments().getString(QUERY)));
        builder.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FeedUtils.saveSearch(getArguments().getString(FEED_ID), getArguments().getString(QUERY), getActivity(), new APIManager(getActivity()));
                SaveSearchFragment.this.dismiss();
            }
        });
        builder.setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SaveSearchFragment.this.dismiss();
            }
        });
        return builder.create();
    }
}