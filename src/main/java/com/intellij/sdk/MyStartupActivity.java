package com.intellij.sdk;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class MyStartupActivity implements ProjectActivity {

    @Nullable
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        ApplicationManager.getApplication().invokeLater(() -> {
            // Create a custom JPanel to enhance the UI of the popup
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            // Set a preferred size for the panel to increase the popup size
            panel.setPreferredSize(new Dimension(500, 300));  // Set desired width and height

            // Add a label with some text
            JLabel messageLabel = new JLabel("Hello, this is your enhanced popup!");
            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(messageLabel, BorderLayout.CENTER);

            // Add a button at the bottom
            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> {
                // Close the popup when the button is clicked
//                popup.cancel();  // Close the popup
            });
            panel.add(okButton, BorderLayout.SOUTH);

            // Create a component popup builder with the custom panel
            var popup = JBPopupFactory.getInstance()
                    .createComponentPopupBuilder(panel, null)
                    .setTitle("Enhanced Popup")
                    .setResizable(true)  // Allow resizing
                    .setMovable(true)     // Allow moving the popup
                    .setRequestFocus(true)
                    .setShowBorder(true)
                    .setCancelOnClickOutside(true) // Close when clicking outside
                    .createPopup();

            // Show the popup at the center of the IDE window
            popup.show(RelativePoint.getCenterOf(
                    WindowManager.getInstance().getIdeFrame(project).getComponent()));
        });

        return Unit.INSTANCE;
    }
}
