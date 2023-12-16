package com.example.employeedetailslist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        // Retrieve employee data from intent
        Intent intent = getIntent();
        Employee employee = intent.getParcelableExtra("employee");

        if (employee != null) {
            populateEmployeeDetails(employee);
        } else {
            Toast.makeText(this, "Failed to get employee details", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void populateEmployeeDetails(final Employee employee) {
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);

        nameTextView.setText(employee.getName());
        emailTextView.setText(employee.getEmail());
        phoneTextView.setText(employee.getPhone());

        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                composeEmail(employee.getEmail());
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + employee.getEmail()));
                startActivity(emailIntent);
            }
        });

        phoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dialPhoneNumber(employee.getPhone());
                Intent dialerIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + employee.getPhone()));
                startActivity(dialerIntent);
            }
        });
    }

   /* private void composeEmail(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        } else {
//            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
        }
    }

    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
//            Toast.makeText(this, "No dialer app found", Toast.LENGTH_SHORT).show();
        }
    }*/
}
