package kakeibo.utils
{
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.formatters.Formatter;
	
	public class ColumnFormatter
	{
		protected var formatter_:Formatter;

		public function ColumnFormatter()
		{
			formatter_ = createFormatter();
		}
		protected function createFormatter():Formatter
		{
			throw new Error("オーバーライドしなくてはいけません。");
		}
		
		public function format(item:Object, column:DataGridColumn):String
		{
			return formatter_.format(item[column.dataField]);	
		}
	}
}
