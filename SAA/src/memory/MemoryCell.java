package memory;
import worldPresentation.*;
import appraisal.*;//EmotionTraits
//import 
import java.util.*;
public class MemoryCell {
	public Event event;
	public LinkedList <EmotionTraits>emo=new LinkedList<EmotionTraits>();
	public MemoryCell(Event event,LinkedList <EmotionTraits>emo){
		this.event=event;
		this.emo=emo;
	}
}
