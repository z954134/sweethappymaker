package shm.circleoflife {
	import mx.controls.HSlider;
	import mx.controls.sliderClasses.Slider;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.SliderEvent;
	
	import shm.common.UICtrlBase;

	public class ColCtrl extends UICtrlBase {
		private var view:ColPanel;

		public var graph:MiniGraph;
		
		protected override function doInitialize(component:UIComponent, id:String):void {
			view = ColPanel(component);
		}

		protected override function onCreationCompleted(event:FlexEvent):void {
			for (var i:int = 1; i <= 10; i++) {
				var slider:HSlider = view['elem' + i] as HSlider;
				slider.addEventListener(SliderEvent.CHANGE, onSliderChanged);
			}
		}

		public function onSliderChanged(event:SliderEvent):void {
			var target:Slider = event.target as Slider;
			var exp:String = target.id.replace("elem", "");
			var idx:int = new int(exp);
			var score:int = target.value;
			graph.ctrl.draw(idx, score);
			
		}
	}
}