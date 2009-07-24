package shm.circleoflife {

	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.controls.HSlider;
	import mx.controls.sliderClasses.Slider;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.SliderEvent;
	import mx.rpc.events.ResultEvent;
	
	import shm.common.UICtrlBase;

	public class ColCtrl extends UICtrlBase {

		public var graph:MiniGraph;
		private var view:ColPanel;

		public function onSelectCompleted(event:ResultEvent):void {
			var col:Object =event.result.col;
			var score:ArrayCollection = col.score as ArrayCollection;
			for (var i:int = 0; i < score.length;i++) {
				var slider:HSlider = view['elem' + (i + 1)] as HSlider;
				var s:Number = new Number(score.getItemAt(i));
				slider.value = s;
				graph.ctrl.draw(i + 1, s);
			}
			view.mostImportant.selectedIndex = col.mostImportant;
			view.nextAction.text = col.nextAction;
			view.lastUpdate.text = col.lastUpdate;
		}

		public function onSliderChanged(event:SliderEvent):void {
			var target:Slider = event.target as Slider;
			var exp:String = target.id.replace("elem", "");
			var idx:int = new int(exp);
			var score:int = target.value;
			graph.ctrl.draw(idx, score);

		}

		public function reset(event:Event):void {
			for (var i:int = 1; i <= 8; i++) {
				var slider:HSlider = view['elem' + i] as HSlider;
				if (slider)	slider.value = 0;
			}
		}

		public function onSaveCompleted(event:ResultEvent):void {
			Alert.show("保存しました。");
			view.selectService.send();
		}


		override protected  function doInitialize(component:UIComponent, id:String):void {
			view = ColPanel(component);
		}

		override protected  function onCreationCompleted(event:FlexEvent):void {
			for (var i:int = 1; i <= 8; i++) {
				var slider:HSlider = view['elem' + i] as HSlider;
				slider.addEventListener(SliderEvent.CHANGE, onSliderChanged);
			}
			view.selectService.send();
		}

	}
}