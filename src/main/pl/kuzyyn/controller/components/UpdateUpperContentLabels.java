package main.pl.kuzyyn.controller.components;

import java.util.Calendar;
import java.util.TreeMap;

import javax.swing.JLabel;

import main.pl.kuzyyn.view.components.ContentPanel;

public class UpdateUpperContentLabels implements GUIUpdateCommand {
	ContentPanel contentPanel;
	public UpdateUpperContentLabels(ContentPanel contentPanel) {
		this.contentPanel=contentPanel;
	}

	@Override
	public <V> void execute(TreeMap<String, V> values) {
		if (values.containsKey("DATE")){
			String date = (String)values.get("DATE");
			String nums[] = date.split("-");
			JLabel valDate = contentPanel.getValidationDateLabel();
			Calendar calendar=Calendar.getInstance();
			try{
			calendar.set(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), Integer.parseInt(nums[2]), 12, 0, 0);
			} catch(IndexOutOfBoundsException e){
				System.out.println("Problem z aktualizowaniem daty");
			}
			contentPanel.setCurrentDate(calendar);
			updateDateLabelContent(valDate,calendar);
	}
		if(values.containsKey("OPERATOR")){
			String operator = (String)values.get("OPERATOR");
			JLabel operatorLabel = contentPanel.getOperatorNameLabel();
			operatorLabel.setText(operator);
		}
	}

	private void updateDateLabelContent(JLabel valDate, Calendar calendar){
		String dateTime = getStringFormatDateTime(calendar);
		valDate.setText(dateTime);
	}

	private String getStringFormatDateTime(Calendar calendar) {
		StringBuilder newDate = new StringBuilder();
		newDate.append(Integer.toString(calendar.get(Calendar.YEAR)));
		newDate.append("-");
		int time= calendar.get(Calendar.MONTH)+1;
		if (time<10){
			newDate.append("0");
			newDate.append(Integer.toString(time));
		} else {
			newDate.append(Integer.toString(time));
		} 
		newDate.append("-");
		time = calendar.get(Calendar.DAY_OF_MONTH);
		if (time<10){
			newDate.append("0");
			newDate.append(Integer.toString(time));

		} else {
			newDate.append(Integer.toString(time));
		}
		newDate.append("  ");
		time=calendar.get(Calendar.HOUR_OF_DAY);
		if (time<10){
			newDate.append("0");
			newDate.append(Integer.toString(time));

		} else {
			newDate.append(Integer.toString(time));
		}
		newDate.append(":");
		time=calendar.get(Calendar.MINUTE);
		if (time<10){
			newDate.append("0");
			newDate.append(Integer.toString(time));

		} else {
			newDate.append(Integer.toString(time));
		}
		return newDate.toString();
	}
	
}
