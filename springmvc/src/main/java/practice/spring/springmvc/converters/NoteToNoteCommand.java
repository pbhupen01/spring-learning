package practice.spring.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.commands.NoteCommand;
import practice.spring.springmvc.model.Note;

@Component
public class NoteToNoteCommand implements Converter<Note, NoteCommand> {

    @Nullable
    @Override
    public NoteCommand convert(Note note) {

        if(note == null)
        {
            return null;
        }
        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(note.getId());
        noteCommand.setNotes(note.getNotes());
        return noteCommand;
    }
}
