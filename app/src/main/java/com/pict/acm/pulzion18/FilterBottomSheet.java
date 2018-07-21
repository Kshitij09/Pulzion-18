package com.pict.acm.pulzion18;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.chip.Chip;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FilterBottomSheet extends BottomSheetDialogFragment {
    boolean tech, nonTech;

    public void setTech(boolean tech) {
        this.tech = !tech;
    }

    public void setNonTech(boolean nonTech) {
        this.nonTech = !nonTech;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.filter_bottom_sheet, container, false);
        Chip techChip = v.findViewById(R.id.ch_technical);
        Chip nonTechChip = v.findViewById(R.id.ch_non_technical);
        markChip(techChip, tech);
        markChip(nonTechChip, nonTech);
        return v;
    }

    private void markChip(Chip chip, Boolean state) {
        if (state) {
            chip.setChipBackgroundColorResource(android.R.color.transparent);
            chip.setChipStrokeColorResource(R.color.colorPrimary);
            chip.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else {
            chip.setChipBackgroundColorResource(R.color.colorPrimary);
            chip.setChipStrokeColorResource(android.R.color.transparent);
            chip.setTextColor(getResources().getColor(android.R.color.white));
        }
    }
}
