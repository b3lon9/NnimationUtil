package kr.co.cavedwellers.nnimationutil
/*
 * Copyright 2023 Neander, Cave-Dwellers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import kr.co.cavedwellers.nnimationutil.data.NDirection
import kr.co.cavedwellers.nnimationutil.data.NDuration
import kr.co.cavedwellers.nnimationutil.protocol.NnimationProtocol

@SuppressLint("ResourceType")
object NnimationUtil: NnimationProtocol {
    override fun overridePendingAlphaOpen(requireActivity: Activity, duration: NDuration) {
        @IdRes val fadeIn = getFadeIn(duration)
        @IdRes val fadeOut = getFadeOut(duration)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            requireActivity.overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, fadeIn, fadeOut)
        } else {
            requireActivity.overridePendingTransition(fadeIn, fadeOut)
        }
    }

    override fun overridePendingAlphaClose(requireActivity: Activity, duration: NDuration) {
        @IdRes val fadeIn = getFadeIn(duration)
        @IdRes val fadeOut = getFadeOut(duration)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            requireActivity.overrideActivityTransition(Activity.OVERRIDE_TRANSITION_CLOSE, fadeOut, fadeIn)
        } else {
            requireActivity.overridePendingTransition(fadeOut, fadeIn)
        }
    }

    /* 현재는 우에서 좌만 구현해놓음*/
    override fun overridePendingTransitionOpen(requireActivity: Activity, duration: NDuration, direct: NDirection) {
        @IdRes val translateIn = getTranslateIn(duration, direct)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            requireActivity.overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, translateIn, R.anim.translate_none)
        } else {
            requireActivity.overridePendingTransition(translateIn, R.anim.translate_none)
        }
    }

    override fun overridePendingTransitionClose(requireActivity: Activity, duration: NDuration, direct: NDirection) {
        @IdRes val translateOut = getTranslateIn(duration, direct)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            requireActivity.overrideActivityTransition(Activity.OVERRIDE_TRANSITION_CLOSE, R.anim.translate_none, translateOut)
        } else {
            requireActivity.overridePendingTransition(R.anim.translate_none, translateOut)
        }
    }

    @IdRes
    private fun getFadeIn(duration:NDuration): Int {
        return when (duration) {
            NDuration.DELAY_250 -> R.anim.fade_in_250
            NDuration.DELAY_500 -> R.anim.fade_in_500
            NDuration.DELAY_750 -> R.anim.fade_in_750
            NDuration.DELAY_1000 -> R.anim.fade_in_1000
        }
    }

    @IdRes
    private fun getFadeOut(duration:NDuration): Int {
        return when (duration) {
            NDuration.DELAY_250 -> R.anim.fade_out_250
            NDuration.DELAY_500 -> R.anim.fade_out_500
            NDuration.DELAY_750 -> R.anim.fade_out_750
            NDuration.DELAY_1000 -> R.anim.fade_out_1000
        }
    }

    @IdRes
    private fun getTranslateIn(duration: NDuration, direct: NDirection): Int {
        return if (direct == NDirection.LEFT) {
            when (duration) {
                NDuration.DELAY_250 -> R.anim.translate_left_in_250
                NDuration.DELAY_500 -> R.anim.translate_left_in_500
                NDuration.DELAY_750 -> R.anim.translate_left_in_750
                NDuration.DELAY_1000 -> R.anim.translate_left_in_1000
            }
        } else {
            // NDirection.RIGHT
            when (duration) {
                NDuration.DELAY_250 -> R.anim.translate_right_in_250
                NDuration.DELAY_500 -> R.anim.translate_right_in_500
                NDuration.DELAY_750 -> R.anim.translate_right_in_750
                NDuration.DELAY_1000 -> R.anim.translate_right_in_1000
            }
        }
    }

    @IdRes
    private fun getTranslateOut(duration: NDuration, direct: NDirection): Int {
        return if (direct == NDirection.LEFT) {
            when (duration) {
                NDuration.DELAY_250 -> R.anim.translate_left_out_250
                NDuration.DELAY_500 -> R.anim.translate_left_out_500
                NDuration.DELAY_750 -> R.anim.translate_left_out_750
                NDuration.DELAY_1000 -> R.anim.translate_left_out_1000
            }
        } else {
            // NDirection.RIGHT
            when (duration) {
                NDuration.DELAY_250 -> R.anim.translate_right_out_250
                NDuration.DELAY_500 -> R.anim.translate_right_out_500
                NDuration.DELAY_750 -> R.anim.translate_right_out_750
                NDuration.DELAY_1000 -> R.anim.translate_right_out_1000
            }
        }
    }
}