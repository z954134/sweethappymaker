package shm.circleoflife {
	import flash.events.Event;
	
	import mx.controls.HSlider;
	import mx.controls.sliderClasses.Slider;
	import mx.core.Application;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.SliderEvent;
	
	import shm.common.UICtrlBase;
	import shm.member.LoginEvent;

	public class ColCtrl extends UICtrlBase {
		private var view:ColPanel;

		public var graph:MiniGraph;

		public function reset(event:Event):void {
			for (var i:int = 1; i <= 10; i++) {
				var slider:HSlider = view['elem' + i] as HSlider;
				if (slider)	slider.value = 0;
			}
		}


		protected override function doInitialize(component:UIComponent, id:String):void {
			view = ColPanel(component);
			Application.application.addEventListener(LoginEvent.LOGIN_COMPLETE, reset);
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