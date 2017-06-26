/**
 * 
 */

// 날짜 형식 변환
function formatDate(cDate, type) {
	var fDate = '';
	if (type == 1) {
		fDate = cDate.getFullYear() + '-'
				+ leadingZeros(cDate.getMonth() + 1, 2) + '-'
				+ leadingZeros(cDate.getDate(), 2) + ' '
				+ leadingZeros(cDate.getHours(), 2) + ":"
				+ leadingZeros(cDate.getMinutes(), 2) + ':'
				+ leadingZeros(cDate.getSeconds(), 2);
	} else if (type == 2) {
		fDate = cDate.getFullYear()
				+ leadingZeros(cDate.getMonth() + 1, 2)
				+ leadingZeros(cDate.getDate(), 2);
	}
	return fDate;
}

// 날짜 형식을 위해 한자리수에 0 붙이기
function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();
	if (n.length < digits) {
	  for (i = 0; i < digits - n.length; i++)
	    zero += '0';
	}
	return zero + n;
}