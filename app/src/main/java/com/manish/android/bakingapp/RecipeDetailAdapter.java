package com.manish.android.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.ViewHolder> {

    Context context;
    private final List<Step> mSteps;
    final private StepListItemClickListener mOnStepListItemClickListener;

    public interface StepListItemClickListener{
        void onItemClick(int pos);
    }
    // private final OnStepsListFragmentInteractionListener mListener;

    /*  public RecipeDetailAdapter(List<Step> items, OnStepsListFragmentInteractionListener listener) {
          mSteps = items;
          mListener = listener;
      }*/


    public RecipeDetailAdapter(List<Step> items, StepListItemClickListener listener, Context context) {
        mSteps = items;
        mOnStepListItemClickListener= listener;
        this.context=context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_fragment, parent, false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Step step = mSteps.get(position);
        String stepShortDescription = step.getShortDescription();
        holder.mStepShortDecsriptionTextView.setText(stepShortDescription);

        //set click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnStepListItemClickListener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }


    //viewholder inner class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView mStepShortDecsriptionTextView;


        public ViewHolder(View view,Context context) {
            super(view);

            mStepShortDecsriptionTextView = (TextView) view.findViewById(R.id.step_desc_tv);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnStepListItemClickListener.onItemClick(clickedPosition);
        }
    }
}
