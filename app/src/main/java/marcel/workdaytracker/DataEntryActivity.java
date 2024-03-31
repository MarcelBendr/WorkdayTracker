package marcel.workdaytracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DataEntryActivity extends AppCompatActivity {

    // Inside DataEntryActivity.java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        Spinner spinnerMonth = findViewById(R.id.spinnerMonth);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(adapter);

        // Handle spinner selection
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the selected month (e.g., January)
                String selectedMonth = parent.getItemAtPosition(position).toString();
                // Calculate the number of days in the selected month (e.g., January)
                int numDays = getDaysInSelectedMonth(selectedMonth); // method at the bottom

                Log.d("DataEntryActivity", "Selected month: " + selectedMonth);
                Log.d("DataEntryActivity", "Number of days: " + numDays);

                // Get your TableLayout
                TableLayout tableLayout = findViewById(R.id.tableLayout);
                tableLayout.removeAllViews(); // Clear existing rows

                // Add rows dynamically
                for (int day = 1; day <= numDays; day++) {
                    //TableRow rowView = new TableRow(DataEntryActivity.this);
                    // Customize row (add TextViews, dropdowns, etc.)
                    // ...
                    View rowView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.row_data_entry, null);

                    // Set a fixed height for each row (adjust as needed)
                    //TableRow row = new TableRow(DataEntryActivity.this);
                    int rowHeightPixels = getResources().getDimensionPixelSize(R.dimen.table_row_height);
                    rowView.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            rowHeightPixels
                    ));

                    TextView tvDay = rowView.findViewById(R.id.tvDay);
                    String dayText = day + "." + selectedMonth;
                    tvDay.setText(dayText);
                    //tvDay.setText("$selectedMonth " + day);

                    // Add the row to the table
                    tableLayout.addView(rowView);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when nothing is selected
                Toast.makeText(getApplicationContext(), "Please select a month", Toast.LENGTH_SHORT).show();
            }

            public void goBackToMainMenu(View view) {
                // Implement your logic to navigate back to the main screen
                // For example:
                // Intent intent = new Intent(this, MainActivity.class);
                // startActivity(intent);
                finish(); // Close the current activity
            }
        });

        /*
        // Get the TableLayout
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        // Calculate the number of days in the selected month (e.g., January)
        int daysInSelectedMonthOld = getDaysInSelectedMonthOld();

        // Inflate rows dynamically
        for (int day = 1; day <= daysInSelectedMonthOld; day++) {
            View rowView = LayoutInflater.from(this).inflate(R.layout.row_data_entry, null);
            TextView tvDay = rowView.findViewById(R.id.tvDay);
            tvDay.setText("Day " + day);

            // Add the row to the table
            tableLayout.addView(rowView);
        }
*/
    }

    private int getDaysInSelectedMonthOld() {
        return 31;
    }

    private int getDaysInSelectedMonth(String selectedMonth) {
        // Implement logic to get the number of days based on the selected month
        // Example: For January, return 31
        // You can use Calendar or other date-related libraries
        if (selectedMonth.contains("Januar")) {
            return 31;
        } else if (selectedMonth.contains("Februar")) {
            return 28;
        } else if (selectedMonth.contains("März")) {
            return 31;
        } else if (selectedMonth.contains("April")) {
            return 30;
        } else if (selectedMonth.contains("Mai")) {
            return 31;
        }
        // Add other months as needed
        return 0; // Default value
    }

/*
    // Find the spinner view
    Spinner spinnerChoice = findViewById(R.id.spinnerChoice);

    // Create an ArrayAdapter to populate the spinner
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinnerChoice.setAdapter(adapter);
*/


}
