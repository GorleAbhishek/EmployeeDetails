package com.example.employeedetailslist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employees;
    private Context context;

    public EmployeeAdapter(Context context) {
        this.context = context;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        if (employees != null) {
            Employee employee = employees.get(position);
            holder.bind(employee);
        }
    }

    @Override
    public int getItemCount() {
        return employees != null ? employees.size() : 0;
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView emailTextView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);

            // Set click listeners
            nameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Employee clickedEmployee = employees.get(position);
                        openEmployeeDetail(clickedEmployee);
                    }
                }
            });

            emailTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Employee clickedEmployee = employees.get(position);
                        composeEmail(clickedEmployee.getEmail());
                    }
                }
            });
        }

        public void bind(Employee employee) {
            nameTextView.setText(employee.getName());
            emailTextView.setText(employee.getEmail());
        }
    }

    private void openEmployeeDetail(Employee employee) {
        Intent intent = new Intent(context, EmployeeDetailActivity.class);
        intent.putExtra("employee", employee);
        context.startActivity(intent);
    }

    private void composeEmail(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}


