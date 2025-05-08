package com.example.listrandom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IntroSliderAdapter extends RecyclerView.Adapter<IntroSliderAdapter.IntroViewHolder> {

    private final List<IntroSlide> introSlides;
    private long animationDuration = 7000; 

    public IntroSliderAdapter(List<IntroSlide> introSlides) {
        this.introSlides = introSlides;
    }

    @NonNull
    @Override
    public IntroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.intro_slide_item, parent, false);
        return new IntroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntroViewHolder holder, int position) {
        holder.bind(introSlides.get(position));
        holder.animateImage();
    }

    @Override
    public int getItemCount() {
        return introSlides.size();
    }

    class IntroViewHolder extends RecyclerView.ViewHolder { 
        private final ImageView imageIcon;
        private final TextView textTitle;
        private final TextView textDescription;

        IntroViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIcon = itemView.findViewById(R.id.imageSlideIcon);
            textTitle = itemView.findViewById(R.id.textSlideTitle);
            textDescription = itemView.findViewById(R.id.textSlideDescription);
        }

        void bind(IntroSlide introSlide) {
            imageIcon.setImageResource(introSlide.getImageResId());
            imageIcon.setAlpha(0f); 

            textTitle.setText(introSlide.getTitle());
            textDescription.setText(introSlide.getDescription());
        }

        void animateImage() {
            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageIcon, "alpha", 0f, 1f);
            alphaAnimator.setDuration(animationDuration);
            alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageIcon, "scaleX", 0.8f, 1f);
            scaleXAnimator.setDuration(animationDuration);
            scaleXAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

            ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageIcon, "scaleY", 0.8f, 1f);
            scaleYAnimator.setDuration(animationDuration);
            scaleYAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
            animatorSet.start();
        }
    }
}
