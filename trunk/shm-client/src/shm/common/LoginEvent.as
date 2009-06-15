package shm.common
{
	import flash.events.Event;
	

	public class LoginEvent extends Event
	{
		public static const LOGIN_COMPLETE:String = "loginComplete";
		
		public function LoginEvent(type:String, bubbles:Boolean = false, cancelable:Boolean = false)
		{
			super(type, bubbles, cancelable);
		}

	}
}