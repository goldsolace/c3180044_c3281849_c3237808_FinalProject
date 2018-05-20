<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="network" class="d-none">
	<div class="form-group">
		<label for="device">Device?</label>
		<input name="device" type="text" class="form-control" placeholder="Chromebook, iPhone 6, Dell Inspiron, etc">
	</div>
	<div class="form-group">
		<label for="device">Location?</label>
		<input name="device" type="text" class="form-control" placeholder="GP Building, MCTH100, EAG01, etc">
	</div>
	<div class="form-group">
		<label for="browser">Browser?</label>
		<select name="browser" id="browser" class="form-control">
			<option value="Chrome">Google Chrome</option>
			<option value="Firefox">Firefox</option>
			<option value="Internet Explorer">Internet Explorer</option>
			<option value="Safari">Safari</option>
			<option value="Opera">Opera</option>
			<option value="Firefox">Microsoft Edge</option>
			<option value="UC Browswer">UC Browswer</option>
			<option value="Other">Other<small class="text-muted">(Please specifiy in description)</small></option>
		</select>
	</div>
	<div class="form-group">
		<label for="website">Website I'm trying to connect to?</label>
		<input name="website" type="text" class="form-control" placeholder="google.com, newcastle.edu.au, etc">
	</div>

	<div class="form-group">
		<label for="website">I am able to access internal websites?</label>
		<div class="form-check form-check-inline">
			<input name="website" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label" for="website">
				Yes
			</label>
		</div>
		<div class="form-check form-check-inline">
			<input name="website"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="website">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="alternate">I have tried using an alternate browser?</label>
		<div class="form-check form-check-inline">
			<input name="alternate" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label" for="alternate">
				Yes
			</label>
		</div>
		<div class="form-check form-check-inline">
			<input name="alternate"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="alternate">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="restart">I have tried restarting my device?</label>
		<div class="form-check form-check-inline">
			<input name="restart" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label" for="restart">
				Yes
			</label>
		</div>
		<div class="form-check form-check-inline">
			<input name="restart"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="restart">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="anotherDevice">Can you access the website on another device?</label>
		<div class="form-check form-check-inline">
			<input name="anotherDevice" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label" for="anotherDevice">
				Yes
			</label>
		</div>
		<div class="form-check form-check-inline">
			<input name="anotherDevice"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="anotherDevice">
				No
			</label>
		</div>
	</div>
</div>
