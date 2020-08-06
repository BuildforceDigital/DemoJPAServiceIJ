/**
 * Sample OpenUI5 Progressive Web Application
 * Simple TODO list
 */
sap.ui.getCore().attachInit(function todoApp() {
	//
	// Model
	//
	let aTasks = {};

	function loadTasks() {
		const json = localStorage.getItem("tasks");
		try {
			aTasks = JSON.parse(json) || {};
		} catch (e) {
			jQuery.sap.log.error(e.message);
		}
	}

	function saveTasks() {
		localStorage.setItem("tasks", JSON.stringify(aTasks));
	}

	function addTask(description) {
		const id = Date.now();
		aTasks[id] = {id: id, t: description};
		saveTasks();
		return aTasks[id];
	}

	function deleteTask(id) {
		delete aTasks[id];
		saveTasks();
	}

	//
	// Controller
	//
	let oListTodo;
	let oInputAddTask;

	function createListItem(mTask) {
		const listItem = new sap.m.DisplayListItem({label: mTask.t}).data("id", mTask.id);
		oListTodo.addAggregation("items", listItem);
	}

	function deleteListItem(oListItem) {
		const id = oListItem.data("id");
		oListTodo.removeAggregation("items", oListItem);
		deleteTask(id);
	}

	function populateList() {
		for (let id in aTasks) {
			createListItem(aTasks[id]);
		}
	}

	function addNewTask() {
		const description = oInputAddTask.getValue();
		if (description) {
			const task = addTask(description);
			createListItem(task);
			oInputAddTask.setValue("");
			addTaskButton.setEnabled(false);
		}
	}

	function onSwipeDelete() {
		deleteListItem(oListTodo.getSwipedItem());
		oListTodo.swipeOut();
	}

	function onDeleteItem(oEvent) {
		deleteListItem(oEvent.getParameter("listItem"));
	}


	//
	// View
	//
	const app = new sap.m.App("myApp");

	oListTodo = new sap.m.List({
		inset: true,
		mode: "Delete",
		noDataText: "Relax, you have no tasks for today :)",
		delete: onDeleteItem,
		swipeContent: new sap.m.Button({
			text: "Delete",
			type: "Reject",
			press: onSwipeDelete
		}),
		items: []
	});

	oInputAddTask = new sap.m.Input("addTaskInput", {
		placeholder: "New task..",
		value: "",
		width: "100%",
		submit: addNewTask,
		liveChange: function (oEvent) {
			addTaskButton.setEnabled(!!oEvent.getParameter("value"));
		}
	});

	var addTaskButton = new sap.m.Button("addTaskButton", {
		text: "Add",
		enabled: false,
		press: addNewTask
	});

	const footer = new sap.m.Toolbar("footer", {
		content: [oInputAddTask, addTaskButton]
	});

	const todoPage = new sap.m.Page("todoPage", {
		title: "UI5 TODO Sample",
		showNavButton: false,
		showFooter: true,
		floatingFooter: true,
		footer: footer,
		content: [oListTodo]
	});

	// Start application
	loadTasks();
	populateList();

	app.addPage(todoPage);
	app.setInitialPage("todoPage");

	app.placeAt("content", "only");
});