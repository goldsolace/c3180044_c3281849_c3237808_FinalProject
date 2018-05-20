<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div id="software" class="d-none">
	<div class="form-group">
		<label for="device">Device?</label>
		<input name="device" type="text" class="form-control" placeholder="Chromebook, iPhone 6, Dell Inspiron, etc">
	</div>
	<div class="form-group">
		<label for="software">Software I'm trying to use?</label>
		<input name="software" type="text" class="form-control" placeholder="MS Word, Adobe Acrobat, etc">
	</div>
	<div class="form-group">
		<label for="software">Software version I'm trying to use?</label>
		<input name="software" type="text" class="form-control" placeholder="2016, CC, etc">
	</div>
	<div class="form-group">
		<label for="install">Can you install the software?</label>
		<div class="form-check form-check-inline">
			<input name="install" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="install">
				Yes
			</label>
			<input name="install"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="install">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="run">Can you run the software?</label>
		<div class="form-check form-check-inline">
			<input name="run" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="run">
				Yes
			</label>
			<input name="run"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="run">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="anotherDevice">Have you tried running the software on another device?</label>
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
