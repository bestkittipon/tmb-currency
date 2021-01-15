package com.tmb.currency.dialog

import android.content.Context
import android.widget.Toast

class LoadingOverlayDialog(private val context: Context) {

  /*private val dialog: Dialog by lazy {
    val dialog = Dialog(context, R.style.NextLoadingOverlay)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(R.layout.layout_loading_overlay)
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    return@lazy dialog
  }*/

  //private val disposeBag = CompositeDisposable()

  /**
   * Overlays the screen with a loading spinner with optional [labels].
   */
  fun show(labels: List<String>) {
    /*if (dialog.isShowing) {
      return
    }

    dialog.show()

    when {
      labels.isEmpty() -> setLabel(label = "", animate = false)
      labels.size == 1 -> setLabel(label = labels[0], animate = false)
      else -> startLabelCycler(labels = labels, interval = LABEL_CYCLE_INTERVAL)
    }*/
    Toast.makeText(context, "Show Loading", Toast.LENGTH_LONG).show()
  }

  /**
   * Dismisses the loading overlay.
   */
  fun dismiss() {
    //if (dialog.isShowing) dialog.dismiss()
    Toast.makeText(context, "Dismiss Loading", Toast.LENGTH_LONG).show()
  }

  /**
   * Begins cycling through the list of [labels] at a specific [interval] in
   * milliseconds.
   */
  private fun startLabelCycler(labels: List<String>, interval: Long) {
    /*Observable.interval(0, interval, TimeUnit.MILLISECONDS)
      .takeWhile { dialog.isShowing }
      .observeOn(AndroidSchedulers.mainThread())
      .map { it.toInt() % labels.size }
      .subscribe { setLabel(label = labels[it], animate = true) }
      .disposedBy(disposeBag)*/
  }

  /**
   * Sets the label in the loading overlay. Set [animate] to true to apply a
   * fade animation.
   *
   * TODO: Text doesn't appear on Android 10
   */
  /*private fun setLabel(label: String, animate: Boolean = false) {
    if (!animate) {
      dialog.tvLabel.text = label
      return
    }

    val animateIn = AlphaAnimation(0f, 1f).also {
      it.duration = FADE_DURATION
    }
    val animateOut = AlphaAnimation(1f, 0f).also {
      it.duration = FADE_DURATION
      it.setAnimationListener(onEnd = {
        dialog.tvLabel.text = label
        dialog.tvLabel.startAnimation(animateIn)
      })
    }

    dialog.tvLabel.startAnimation(animateOut)
  }

  companion object {
    private const val LABEL_CYCLE_INTERVAL = 3_000L
    private const val FADE_DURATION = 300L
  }*/

}
