package practice.spring.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import practice.spring.springmvc.commands.NoteCommand;
import practice.spring.springmvc.model.Note;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Nullable
    @Override
    public Note convert(NoteCommand noteCommand) {

        if(noteCommand == null)
        {
            return null;
        }

        Note note = new Note();
        note.setId(noteCommand.getId());
        note.setNotes(noteCommand.getNotes());
        return note;
    }
}
