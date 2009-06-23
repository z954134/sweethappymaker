package shm.okdialy {
	import flash.events.Event;
	

	public class OkDialyEvent extends Event {
		
		public static const LOAD_COMPLETE:String = "loadComplete";
		public static const LOAD_REQUIRED:String = "loadRequired";
		
		public var requiredDate:String
		public function OkDialyEvent(type:String, bubbles:Boolean = false, cancelable:Boolean = false) {
			super(type, bubbles, cancelable);
		}

	}
}