package shm.member
{
	import flash.events.Event;
	

	public class LoginEvent extends Event
	{
		public static const LOGIN_COMPLETE:String = "loginComplete";

		public var success:Boolean = false;
				
		public var memberId:String;
		
		public var memberKey:String;
		 
		public function LoginEvent(type:String, bubbles:Boolean = false, cancelable:Boolean = false)
		{
			super(type, bubbles, cancelable);
		}

	}
}