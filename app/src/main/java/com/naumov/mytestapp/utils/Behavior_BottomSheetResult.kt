package com.naumov.mytestapp.utils

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout

class Behavior_BottomSheetResult:CoordinatorLayout.Behavior<ConstraintLayout>() {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: ConstraintLayout,
        dependency: View
    ): Boolean {
        return super.layoutDependsOn(parent, child, dependency)
    }


}