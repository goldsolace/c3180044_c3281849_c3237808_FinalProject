<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div id="hardware" class="d-none">
	<div class="form-group">
		<label for="device">Device you're trying to use?</label>
		<input name="device" type="text" class="form-control" placeholder="Printer, iMac, etc">
	</div>
	<div class="form-group">
		<label for="device">Location?</label>
		<input name="device" type="text" class="form-control" placeholder="GP Building, MCTH100, EAG01, etc">
	</div>
	<div class="form-group">
		<label for="access">Can you access the device with your account login?</label>
		<div class="form-check form-check-inline">
			<input name="access" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="access">
				Yes
			</label>
			<input name="access"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="access">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="damaged">Is the device damaged?</label>
		<div class="form-check form-check-inline">
			<input name="damaged" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="damaged">
				Yes
			</label>
			<input name="damaged"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="damaged">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="power">Does the device power on?</label>
		<div class="form-check form-check-inline">
			<input name="power" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="power">
				Yes
			</label>
			<input name="power"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="power">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="error">Error message if displayed?</label>
		<input name="error" type="text" class="form-control" placeholder="e.g. 0xc05d1281">
	</div>
</div>
