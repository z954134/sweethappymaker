package shm.okdialy {
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.UIComponent;
	import mx.events.CalendarLayoutChangeEvent;
	import mx.events.FlexEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.ResultEvent;
	
	import shm.common.UICtrlBase;


	public class DailyInputFormCtrl extends UICtrlBase {


		private var view:DailyInputForm;

		private var dateFormatter:DateFormatter = new DateFormatter();

		protected function reset(event:Event):void {
			clearForm();
			view.dateChooser.selectedDate = new Date();
			view.dateChooser.highlight();
		}

		protected override function doInitialize(component:UIComponent, id:String):void {
			view = DailyInputForm(component);
			dateFormatter.format("YYYY/MM/DD");
		}

		protected override function onCreationCompleted(event:FlexEvent):void {
			view.okDialyDateText.text = dateFormatter.format(new Date());
			view.addEventListener(OkDialyEvent.LOAD_REQUIRED, onLoadRequired);
			view.hintService.send();
			view.monthlyListService.send();
		}

		public function onHintCompleted(event:ResultEvent):void {
			var hint:Object = event.result.hint;
			view.previousHintKey = hint.key;
			view.hintText.text = hint.value;
		}

		public function onDateChooserChanged(event:CalendarLayoutChangeEvent):void {
			var selected:Date = view.dateChooser.selectedDate;
			select(dateFormatter.format(selected));
		}
		
		public function onMonthlyListLoaded(event:ResultEvent):void {
			var r:Object = event.result.monthlyDialyDays;
			if (!r) {
				return;
			}
			var days:ArrayCollection = extractDialyDays(r);
			view.dateChooser.setHilightDays(days.source);
			view.dateChooser.highlight();
		}
		
		private function extractDialyDays(r:Object):ArrayCollection {
			var dialyDays:ArrayCollection = null;
			if (r.dialyDate is ArrayCollection) {
				dialyDays = r.dialyDate as ArrayCollection;
			} else {
				dialyDays = new ArrayCollection();
				dialyDays.addItem(r.dialyDate);
			}
			return dialyDays;
		}
		


		public function onLoadRequired(event:OkDialyEvent):void {
			var dt:String = event.requiredDate;
			view.dateChooser.selectedDate = new Date(dt);
			select(dt);
		}

		private function select(dt:String):void {
			view.okDialyDateText.text = dt;
			view.selectService.send();
		}

		public function onSelectCompleted(event:ResultEvent):void {

			clearForm();

			var dialy:Object = event.result.okDialy;
			var items:ArrayCollection = dialy.item as ArrayCollection;
			if (items == null) {
				return;
			}
			for (var i:int = 0; i < items.length; i++) {
				view['ok' + (i + 1)].text = items[i];
			}
		}

		private function clearForm():void {
			for (var i:int = 1; i <= 10; i++) {
				view['ok' + i].text = "";
			}
		}
	}
}