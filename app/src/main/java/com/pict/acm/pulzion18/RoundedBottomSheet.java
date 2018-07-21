package com.pict.acm.pulzion18;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;

public class RoundedBottomSheet extends BottomSheetDialogFragment {
    @Override
    public int getTheme() {
        return R.style.Widget_Pulzion18_BottomSheet;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), getTheme());
    }
}
