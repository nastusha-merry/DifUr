package ru.bmstu.nastasia.difur.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import org.mariuszgromada.math.mxparser.Function;
import ru.bmstu.nastasia.difur.R;

import java.util.ArrayList;

public class System_simple extends Fragment {

    private Button button_solve;
    private Button button_ok;
    private EditText row_number_et;
    private RecyclerView rows_rv;
    private FunctionAdapter function_adapter;
    private int rows_number;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_system_simple, container, false);

        initFields(view);


        return view;
    }

    private void initFields(View view) {
        context = getContext();
        row_number_et = view.findViewById(R.id.system_row_number_et);
        rows_number = 2;  // default
        function_adapter = new FunctionAdapter(rows_number);
        rows_rv = view.findViewById(R.id.system_simple_rv);
        rows_rv.setAdapter(function_adapter);
        rows_rv.setLayoutManager(new LinearLayoutManager(this.context));

        button_ok = view.findViewById(R.id.system_ok_button);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRowsNumber();
                function_adapter.update(rows_number);
                function_adapter.notifyDataSetChanged();

            }
        });

        button_solve = view.findViewById(R.id.system_solve_button);
        button_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFunctions();
                ArrayList<FunctionInputListener> listeners = function_adapter.getInputListeners();
                boolean is_ok = true;
                ArrayList<Function> functions = new ArrayList<>(listeners.size());
                for (FunctionInputListener listener: listeners) {
                    Boolean val = listener.checkVal();
                    if (val != null) {
                        is_ok &= val;
                        functions.add(listener.getFunction());
                    }
                }
                if (!is_ok) {
                    Toast.makeText(context, R.string.warning_incorrect, Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (Function f: functions) {
                    sb.append(f.toString() + "\n");
                }
                Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();

            }
        });


        if (context == null) {
            throw new RuntimeException("I_simple.initFields: Null context");
        }

    }

    private int updateRowsNumber() {
        rows_number = Integer.parseInt(row_number_et.getText().toString());
        return rows_number;
    }

    private boolean checkFunctions() {
        boolean res = true;
        for (int i = 0; i < rows_number; ++i) {
            res &= ((FunctionAdapter.InputHolder)rows_rv.findViewHolderForAdapterPosition(i)).checkInput();
        }
        Toast.makeText(context, ""+res, Toast.LENGTH_SHORT).show();
        return res;
    }

}