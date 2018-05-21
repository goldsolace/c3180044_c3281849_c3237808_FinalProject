<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div id="hardware" class="d-none">
	<div class="form-group">
		<label for="hardwareDevice">Device you're trying to use?</label>
		<input name="hardwareDevice" type="text" class="form-control" placeholder="Printer, iMac, etc">
	</div>
	<div class="form-group">
		<label for="hardwareLocation">Location?</label>
		<input name="hardwareLocation" type="text" class="form-control" placeholder="GP Building, MCTH100, EAG01, etc">
	</div>
	<div class="form-group">
		<label for="hardwareAccess">Can you access the device with your account login?</label>
		<div class="form-check form-check-inline">
			<input name="hardwareAccess" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="hardwareAccess">
				Yes
			</label>
			<input name="hardwareAccess"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="hardwareAccess">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="hardwareDamaged">Is the device damaged?</label>
		<div class="form-check form-check-inline">
			<input name="hardwareDamaged" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="hardwareDamaged">
				Yes
			</label>
			<input name="hardwareDamaged"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="hardwareDamaged">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="hardwarePower">Does the device power on?</label>
		<div class="form-check form-check-inline">
			<input name="hardwarePower" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="hardwarePower">
				Yes
			</label>
			<input name="hardwarePower"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="hardwarePower">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="error">Error message if displayed?</label>
		<input name="error" type="text" class="form-control" placeholder="e.g. 0xc05d1281">
	</div>
</div>
