package com.company.pm.screen.subtask;

import io.jmix.ui.screen.*;
import com.company.pm.entity.Subtask;

@UiController("pm_Subtask.edit")
@UiDescriptor("subtask-edit.xml")
@EditedEntityContainer("subtaskDc")
public class SubtaskEdit extends StandardEditor<Subtask> {
}