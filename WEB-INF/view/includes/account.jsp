<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div id="account" class="d-none">
	<div class="form-group">
		<label for="activate">Have you activated your account?</label>
		<div class="form-check form-check-inline">
			<input name="activate" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="activate">
				Yes
			</label>
			<input name="activate"  class="form-check-input mr-1" type="radio" value="No">
			<label class="form-check-label" for="activate">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="university">Can you log into a university computer?</label>
		<div class="form-check form-check-inline">
			<input name="university" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="university">
				Yes
			</label>
			<input name="university"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="university">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="system">University system you're trying to access?</label>
		<input name="system" type="text" class="form-control" placeholder="e.g. NuMail, Myhub, etc">
	</div>
	<div class="form-group">
		<label for="reset">Have you tried resetting your password?</label>
		<div class="form-check form-check-inline">
			<input name="reset" class="form-check-input" type="radio" value="Yes">
			<label class="form-check-label mr-1" for="reset">
				Yes
			</label>
			<input name="reset"  class="form-check-input" type="radio" value="No">
			<label class="form-check-label" for="reset">
				No
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="error">Error message if displayed?</label>
		<input name="error" type="text" class="form-control" placeholder="e.g. Your username is not recognised Error: 3001">
	</div>
</div>
