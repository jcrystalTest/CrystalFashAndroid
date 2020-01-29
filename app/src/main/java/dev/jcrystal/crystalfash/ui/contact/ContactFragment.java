package dev.jcrystal.crystalfash.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import dev.jcrystal.crystalfash.R;
import jcrystal.mobile.net.controllers.ManagerCart;
import jcrystal.mobile.net.controllers.ManagerContact;

public class ContactFragment extends Fragment {

    Button btnContact;
    EditText editName, editEmail, editMessage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact, container, false);
        btnContact = root.findViewById(R.id.btn_contact_id);
        editName = root.findViewById(R.id.txt_name_id);
        editEmail = root.findViewById(R.id.txt_email_id);
        editMessage = root.findViewById(R.id.txt_message_id);

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManagerContact.contact(getActivity(),editName.getText().toString(), editEmail.getText().toString(), editMessage.getText().toString(), ()->{
                    Toast.makeText(getContext(), "Message send to server", Toast.LENGTH_LONG).show();
                    clearData();
                }, error -> {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                });

            }
        });
        return root;
    }

    private void clearData() {
        editName.getText().clear();
        editEmail.getText().clear();
        editMessage.getText().clear();
    }
}