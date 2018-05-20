<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div id="network" class="d-none">
	<div class="form-group">
		<label for="device">Device?</label>
		<input name="device" type="text" class="form-control" placeholder="Chromebook, iPhone 6, Dell Inspiron, etc">
	</div>
	<div class="form-group">
		<label for="location">Location?</label>
		<input name="location" type="text" class="form-control" placeholder="GP Building, MCTH100, EAG01, etc">
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
			<option value="Other">Other (Please specifiy in description)</option>
		</select>
	</div>
	<div class="form-group">
		<label for="website">Website you're trying to connect to?</label>
		<input name="website" type="text" class="form-control" placeholder="google.com, newcastle.edu.au, etc">
	</div>

	<div class="form-group">
		<label for="website">Are you able to access internal websites?</label>
		<div class="form-check form-check-inline">
			<input name="website" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="website">
				Yes
			</label>
			<input name="website"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="website">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="alternate">Have you tried using an alternate browser?</label>
		<div class="form-check form-check-inline">
			<input name="alternate" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="alternate">
				Yes
			</label>
			<input name="alternate"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="alternate">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="restart">Have you tried restarting my device?</label>
		<div class="form-check form-check-inline">
			<input name="restart" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="restart">
				Yes
			</label>
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
			<label class="form-check-label mr-1" for="anotherDevice">
				Yes
			</label>
			<input name="anotherDevice"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="anotherDevice">
				No
			</label>
		</div>
	</div>
</div>
