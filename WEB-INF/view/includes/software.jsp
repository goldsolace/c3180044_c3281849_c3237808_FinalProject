<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div id="software" class="d-none">
	<div class="form-group">
		<label for="softwareDevice">Device?</label>
		<input name="softwareDevice" type="text" class="form-control" placeholder="Chromebook, iPhone 6, Dell Inspiron, etc">
	</div>
	<div class="form-group">
		<label for="softwareSoftware">Software I'm trying to use?</label>
		<input name="softwareSoftware" type="text" class="form-control" placeholder="MS Word, Adobe Acrobat, etc">
	</div>
	<div class="form-group">
		<label for="softwareVersion">Software version I'm trying to use?</label>
		<input name="softwareVersion" type="text" class="form-control" placeholder="2016, CC, etc">
	</div>
	<div class="form-group">
		<label for="softwareInstall">Can you install the software?</label>
		<div class="form-check form-check-inline">
			<input name="softwareInstall" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="softwareInstall">
				Yes
			</label>
			<input name="softwareInstall"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="softwareInstall">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="softwareRun">Can you run the software?</label>
		<div class="form-check form-check-inline">
			<input name="softwareRun" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="softwareRun">
				Yes
			</label>
			<input name="softwareRun"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="softwareRun">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="softwareAnotherDevice">Have you tried running the software on another device?</label>
		<div class="form-check form-check-inline">
			<input name="softwareAnotherDevice" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="softwareAnotherDevice">
				Yes
			</label>
			<input name="softwareAnotherDevice"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="softwareAnotherDevice">
				No
			</label>
		</div>
	</div>
</div>
