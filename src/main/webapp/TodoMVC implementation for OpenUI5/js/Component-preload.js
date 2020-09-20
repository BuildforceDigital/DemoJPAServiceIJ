jQuery.sap.registerPreloadedModules({
	"version": "2.0",
	"name": "js/Component-preload",
	"modules": {
		"js/Component.js": "sap.ui.define([\"sap/ui/core/UIComponent\"],function(t){\"use strict\";return t.extend(\"ToDoMVC.Component\",{metadata:{manifest:\"json\"},init:function(){t.prototype.init.apply(this,arguments),this.getRouter().initialize()}})});",
		"js/app.js": "!function(){\"use strict\";Function.prototype.bind||(Function.prototype.bind=function(t){if(\"function\"!=typeof this)throw new TypeError(\"Function.prototype.bind - what is trying to be bound is not callable\");var o=Array.prototype.slice.call(arguments,1),n=this,e=function(){},i=function(){return n.apply(this instanceof e?this:t,o.concat(Array.prototype.slice.call(arguments)))};return this.prototype&&(e.prototype=this.prototype),i.prototype=new e,i}),sap.ui.getCore().attachInit(function(){new sap.m.Shell({app:new sap.ui.core.ComponentContainer({height:\"100%\",name:\"ToDoMVC\"})}).placeAt(\"openui5\")})}(window);",
		"js/control/ToDoControl.js": "sap.ui.define([\"sap/ui/core/Control\"],function(e){\"use strict\";var t=13,o=27;return e.extend(\"example.control.ToDoControl\",{metadata:{properties:{toDosObject:{type:\"object[]\",defaultValue:[]},filter:{type:\"string\",defaultValue:\"all\"}},events:{addToDoPressed:{enablePreventDefault:!0,parameters:{title:{type:\"string\"}}},deleteToDoPressed:{enablePreventDefault:!0,parameters:{toDoId:{type:\"int\"}}},deleteAllCompletedToDosPressed:{enablePreventDefault:!0,toDoIds:{type:\"int[]\"}},completedToDoPressed:{enablePreventDefault:!0,parameters:{toDoId:{type:\"int\"},completed:{type:\"boolean\"}}},toDoChangedPressed:{enablePreventDefault:!0,parameters:{toDoId:{type:\"int\"},title:{type:\"string\"}}}}},onAddToDo:function(e){if(e.keyCode===t){var o=e.target.value.trim();if(!o||0===o.length)return;this.fireEvent(\"addToDoPressed\",{title:o}),this.rerender()}},onDeleteToDo:function(e){var t=e.target.id.replace(\"destroyToDo-\",\"\");this.fireEvent(\"deleteToDoPressed\",{toDoId:parseInt(t.split(\"-\")[0])}),this.rerender()},_getAllToDosWithStatus:function(e){for(var t=this.getToDosObject(),o=[],i=0;i<t.length;i++)t[i].completed===e&&o.push(parseInt(t[i].id));return o},onDeleteAllCompletedToDos:function(){this.fireEvent(\"deleteAllCompletedToDosPressed\",{toDoIds:this._getAllToDosWithStatus(!0)}),this.rerender()},onToggleToDo:function(e){var t=e.target.id.replace(\"completeToDo-\",\"\");this.fireEvent(\"completedToDoPressed\",{toDoId:parseInt(t.split(\"-\")[0]),completed:e.target.checked}),this.rerender()},onEditToDo:function(e){var t=e.target.id.replace(\"labelFor-\",\"\"),o=e.target.id.replace(\"labelFor-\",\"editField-\");document.getElementById(t).classList.add(\"editing\"),document.getElementById(o).focus()},_onEditKeyPress:function(e){e.keyCode===t?this.onLeaveEditToDoFocus(e):e.keyCode===o&&this.rerender()},onLeaveEditToDoFocus:function(e){var t=e.target.id.split(\"-\")[1],o=e.target.value.trim();o&&0!==o.length?this.fireEvent(\"toDoChangedPressed\",{toDoId:parseInt(t),title:o}):this.fireEvent(\"deleteToDoPressed\",{toDoId:parseInt(t)}),this.rerender()},onToggleAllToDos:function(){var e=this._getAllToDosWithStatus(!1),t=this.getToDosObject();if(e.length>0)for(var o=0;o<e.length;o++)this.fireEvent(\"completedToDoPressed\",{toDoId:e[o],completed:!0});else for(var i=0;i<t.length;i++)this.fireEvent(\"completedToDoPressed\",{toDoId:t[i].id,completed:!1});this.rerender()},onAfterRendering:function(){var e=this.getId(),t=this.getToDosObject();$(\"#inputToDo-\"+e).keypress(this.onAddToDo.bind(this)),$(\"#inputToDoToggle-\"+e).click(this.onToggleAllToDos.bind(this)),$(\"#deleteAllCompleted-\"+e).click(this.onDeleteAllCompletedToDos.bind(this));for(var o=0;o<t.length;o++)$(\"#destroyToDo-\"+t[o].id+\"-\"+e).click(this.onDeleteToDo.bind(this)),$(\"#labelFor-\"+t[o].id+\"-\"+e).dblclick(this.onEditToDo.bind(this)),$(\"#editField-\"+t[o].id+\"-\"+e).focusout(this.onLeaveEditToDoFocus.bind(this)),$(\"#editField-\"+t[o].id+\"-\"+e).keydown(this._onEditKeyPress.bind(this)),$(\"#completeToDo-\"+t[o].id+\"-\"+e).click(this.onToggleToDo.bind(this));document.getElementById(\"inputToDo-\"+this.sId).focus()},renderer:{render:function(e,t){var o,i=t.getId(),l=t.getToDosObject(),r=t.getFilter(),s=l&&l.length>0?!0:!1,d=t._getAllToDosWithStatus(!1),n=t.getModel(\"i18n\").getResourceBundle();if(e.write(\"<div\"),e.writeControlData(t),e.write(\">\"),e.write('<section class=\"todoapp\">'),e.write('<header class=\"header\">'),e.write(\"<h1>\"+n.getText(\"title\")+\"</h1>\"),e.write('<input id=\"inputToDo-'+i+'\" class=\"new-todo\" placeholder=\"'+n.getText(\"newTodo\")+'\" autofocus>'),e.write(\"</header>\"),s?e.write('<section class=\"main\">'):e.write('<section class=\"main\" style=\"display:none;\">'),0===d.length?e.write('<input id=\"inputToDoToggle-'+i+'\" class=\"toggle-all\" type=\"checkbox\" checked>'):e.write('<input id=\"inputToDoToggle-'+i+'\" class=\"toggle-all\" type=\"checkbox\">'),e.write('<label id=\"labelToDo-'+i+'\" for=\"toggle-all\">'+n.getText(\"markAllAsComplete\")+\"</label>\"),e.write('<ul id=\"listToDo\" class=\"todo-list\">'),s)for(var a=0;a<l.length;a++){var c=l[a],p=\"all\"===r||\"completed\"===r&&c.completed||\"active\"===r&&!c.completed;p&&(c.completed?e.write('<li id=\"'+c.id+\"-\"+i+'\" class=\"completed\">'):e.write('<li id=\"'+c.id+\"-\"+i+'\">'),e.write('<div class=\"view\">'),c.completed?e.write('<input id=\"completeToDo-'+c.id+\"-\"+i+'\" class=\"toggle\" type=\"checkbox\" checked>'):e.write('<input id=\"completeToDo-'+c.id+\"-\"+i+'\" class=\"toggle\" type=\"checkbox\">'),e.write('<label id=\"labelFor-'+c.id+\"-\"+i+'\">'+c.title+\"</label>\"),e.write('<button id=\"destroyToDo-'+c.id+\"-\"+i+'\" class=\"destroy\"></button>'),e.write(\"</div>\"),e.write('<input id=\"editField-'+c.id+\"-\"+i+'\" class=\"edit\" value=\"'+c.title+'\">'),e.write(\"</li>\"))}e.write(\"</ul>\"),e.write(\"</section>\"),s?(e.write('<footer id=\"footer\" class=\"footer\">'),e.write('<span id=\"toDoCounter\" class=\"todo-count\">'),o=1===d.length?n.getText(\"singleLeft\"):n.getText(\"multipleLeft\"),e.write(\"<strong>\"+d.length+\"</strong> \"+o),e.write(\"</span>\"),e.write('<ul class=\"filters\">'),e.write(\"<li>\"),\"all\"===r?e.write('<a class=\"selected\" href=\"#/\">'+n.getText(\"all\")+\"</a>\"):e.write('<a href=\"#/\">'+n.getText(\"all\")+\"</a>\"),e.write(\"</li>\"),e.write(\"<li>\"),\"active\"===r?e.write('<a class=\"selected\" href=\"#/active\">'+n.getText(\"active\")+\"</a>\"):e.write('<a href=\"#/active\">'+n.getText(\"active\")+\"</a>\"),e.write(\"</li>\"),e.write(\"<li>\"),\"completed\"===r?e.write('<a class=\"selected\" href=\"#/completed\">'+n.getText(\"completed\")+\"</a>\"):e.write('<a href=\"#/completed\">'+n.getText(\"completed\")+\"</a>\"),e.write(\"</li>\"),e.write(\"</ul>\"),l.length-d.length>0&&e.write('<button id=\"deleteAllCompleted-'+i+'\" class=\"clear-completed\">'+n.getText(\"clearCompleted\")+\"</button>\"),e.write(\"</footer>\")):e.write('<footer id=\"footer\" class=\"footer\" style=\"display:none;\"></footer>'),e.write(\"</section>\"),e.write('<footer class=\"info\">'),e.write(\"<p>\"+n.getText(\"doubleClickInfo\")+\"</p>\"),e.write(\"<p>\"+n.getText(\"credits\")+' <a href=\"https://github.com/agraebe\">Alexander Graebe</a> &amp; <a href=\"https://github.com/alexis90\">Alexander Hauck</a></p>'),e.write(\"<p>\"+n.getText(\"partOf\")+' <a href=\"http://todomvc.com\">TodoMVC</a></p>'),e.write(\"</footer>\"),e.write(\"</div>\")}}})});",
		"js/controller/App.controller.js": "sap.ui.define([\"sap/ui/core/mvc/Controller\"],function(e){\"use strict\";return e.extend(\"ToDoMVC.controller.App\",{})});",
		"js/controller/Home.controller.js": "sap.ui.define([\"sap/ui/core/mvc/Controller\",\"../control/ToDoControl\"],function(e,t){\"use strict\";return jQuery.sap.require(\"jquery.sap.storage\"),e.extend(\"ToDoMVC.controller.Home\",{onInit:function(){var e=sap.ui.core.UIComponent.getRouterFor(this);e.getRoute(\"appHomeFilter\").attachPatternMatched(this._onObjectMatched,this),e.getRoute(\"appHome\").attachPatternMatched(this._onObjectMatched,this);var o=jQuery.sap.storage(jQuery.sap.storage.Type.local);localStorage.getItem(\"0\")||localStorage.setItem(\"0\",0);var r=new sap.ui.model.json.JSONModel({allToDos:[],filterMode:\"all\"});this.getView().setModel(r),o.get(\"todos-openui5\")?r.setData(o.get(\"todos-openui5\")):o.put(\"todos-openui5\",r.getData());var a=new t({toDosObject:\"{/allToDos}\",filter:\"{/filterMode}\",addToDoPressed:function(e){localStorage.setItem(\"0\",parseInt(localStorage.getItem(\"0\"))+1),r.getProperty(\"/allToDos\").push({id:parseInt(localStorage.getItem(\"0\")),title:e.getParameter(\"title\"),completed:!1}),o.put(\"todos-openui5\",r.getData()),r.refresh(!0)},deleteToDoPressed:function(e){for(var t=r.getProperty(\"/allToDos\"),a=0;a<t.length;a++)if(t[a].id===e.getParameter(\"toDoId\")){t.splice(a,1);break}o.put(\"todos-openui5\",r.getData()),r.refresh(!0)},deleteAllCompletedToDosPressed:function(e){for(var t=r.getProperty(\"/allToDos\"),a=e.getParameter(\"toDoIds\");a.length>0;)for(var s=0;s<t.length;s++)if(a[0]===t[s].id){t.splice(s,1),a.splice(0,1);break}o.put(\"todos-openui5\",r.getData()),r.refresh(!0)},completedToDoPressed:function(e){for(var t=r.getProperty(\"/allToDos\"),a=0;a<t.length;a++)if(t[a].id===e.getParameter(\"toDoId\")){t[a].completed=e.getParameter(\"completed\");break}o.put(\"todos-openui5\",r.getData()),r.refresh(!0)},toDoChangedPressed:function(e){for(var t=r.getProperty(\"/allToDos\"),a=0;a<t.length;a++)if(t[a].id===e.getParameter(\"toDoId\")){t[a].title=e.getParameter(\"title\");break}o.put(\"todos-openui5\",r.getData()),r.refresh(!0)}});this.getView().getContent()[0].addContent(a)},_onObjectMatched:function(e){if(e.getParameter(\"arguments\").filter){var t=e.getParameter(\"arguments\").filter.replace(/[^\\w]/gi,\"\");(\"active\"===t||\"completed\"===t)&&this.getView().getModel().setProperty(\"/filterMode\",t)}else this.getView().getModel().setProperty(\"/filterMode\",\"all\");jQuery.sap.storage(jQuery.sap.storage.Type.local).put(\"todos-openui5\",this.getView().getModel().getData()),this.getView().getModel().refresh(!0)}})});",
		"js/manifest.json": "{\"_version\":\"1.21.0\",\"sap.app\":{\"_version\":\"1.21.0\",\"id\":\"ToDoMVC\",\"type\":\"application\",\"i18n\":\"i18n/i18n.properties\",\"applicationVersion\":{\"version\":\"1.0.0\"}},\"sap.ui\":{\"_version\":\"1.1.0\",\"technology\":\"UI5\",\"deviceTypes\":{\"desktop\":true,\"tablet\":true,\"phone\":true}},\"sap.ui5\":{\"_version\":\"1.1.0\",\"rootView\":{\"viewName\":\"ToDoMVC.view.App\",\"type\":\"XML\"},\"dependencies\":{\"minUI5Version\":\"1.79.2\",\"libs\":{\"sap.m\":{}}},\"models\":{\"i18n\":{\"type\":\"sap.ui.model.resource.ResourceModel\",\"settings\":{\"bundleName\":\"ToDoMVC.i18n.i18n\"}}},\"routing\":{\"config\":{\"routerClass\":\"sap.m.routing.Router\",\"viewType\":\"XML\",\"viewPath\":\"ToDoMVC.view\",\"controlId\":\"app\",\"controlAggregation\":\"pages\"},\"routes\":[{\"pattern\":\"\",\"name\":\"appHome\",\"target\":\"home\"},{\"pattern\":\"{filter}\",\"name\":\"appHomeFilter\",\"target\":\"home\"}],\"targets\":{\"home\":{\"viewName\":\"Home\",\"viewLevel\":1}}}}}",
		"js/view/App.view.xml": "<mvc:View controllerName=\"ToDoMVC.controller.App\" xmlns=\"sap.m\" xmlns:mvc=\"sap.ui.core.mvc\" displayBlock=\"true\"><App id=\"app\"/></mvc:View>\n",
		"js/view/Home.view.xml": "<mvc:View controllerName=\"ToDoMVC.controller.Home\" xmlns:mvc=\"sap.ui.core.mvc\" xmlns=\"sap.m\"><Page showHeader=\"false\"/></mvc:View>\n",
		"js/i18n/i18n_en.properties": "# Component\nappTitle=OpenUI5 • TodoMVC\nappDescription=\n\n# Custom Control\ntitle=todos\nnewTodo=What needs to be done?\nmarkAllAsComplete=Mark all as complete\nclearCompleted=Clear completed\nall=All\nactive=Active\ncompleted=Completed\ndoubleClickInfo=Double-click to edit a todo\ncredits=Written by\npartOf=Part of\nmultipleLeft=items left\nsingleLeft=item left\n"
	}
});