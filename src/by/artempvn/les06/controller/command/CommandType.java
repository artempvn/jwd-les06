package by.artempvn.les06.controller.command;

import by.artempvn.les06.controller.command.impl.AddBookCommand;
import by.artempvn.les06.controller.command.impl.FindByTagCommand;
import by.artempvn.les06.controller.command.impl.RemoveBookCommand;
import by.artempvn.les06.controller.command.impl.SortByTagCommand;

public enum CommandType {

	ADD_BOOK {
		{
			this.command = new AddBookCommand();
		}
	},
	REMOVE_BOOK {
		{
			this.command = new RemoveBookCommand();
		}
	},
	SORT_BY_TAG {
		{
			this.command = new SortByTagCommand();
		}
	},
	FIND_BY_TAG {
		{
			this.command = new FindByTagCommand();
		}
	};

	protected Command command;

	public Command getCommand() {
		return command;
	}
}
