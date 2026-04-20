package edu.uga.cs.project4;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuestionPagerAdapter extends FragmentStateAdapter {

    private final Quiz quiz;
    public QuestionPagerAdapter(@NonNull Fragment fragment, Quiz quiz) {
        super(fragment);
        this.quiz = quiz;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return QuestionFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return quiz.getQuestions().size();
    }
}
