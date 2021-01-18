package ro.brinzas.rest_backend.services.grpc;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ro.brinzas.rest_backend.dto.IntakeIntervalDTO;
import ro.brinzas.rest_backend.dto.MedicationDTO;
import ro.brinzas.rest_backend.dto.MedicationPlanNoPatientDTO;
import ro.brinzas.rest_backend.entities.IntakeInterval;
import ro.brinzas.rest_backend.entities.IntakeTaken;
import ro.brinzas.rest_backend.grpc.Empty;
import ro.brinzas.rest_backend.grpc.IntakeIntervalRPCDTO;
import ro.brinzas.rest_backend.grpc.MedicationPlanRPCDTO;
import ro.brinzas.rest_backend.grpc.MedicationPlanRequest;
import ro.brinzas.rest_backend.grpc.MedicationPlanResponse;
import ro.brinzas.rest_backend.grpc.MedicationRPCDTO;
import ro.brinzas.rest_backend.grpc.MedicationTakenMessage;
import ro.brinzas.rest_backend.repositories.IntakeIntervalRepository;
import ro.brinzas.rest_backend.repositories.IntakeTakenRepository;
import ro.brinzas.rest_backend.grpc.MedicationRPCServiceGrpc.MedicationRPCServiceImplBase;
import ro.brinzas.rest_backend.services.MedicationPlanService;
import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MedicationRPCServiceImpl extends MedicationRPCServiceImplBase {
	
	private final MedicationPlanService medicationPlanService;
	private final IntakeIntervalRepository intakeIntervalRepository;
	private final IntakeTakenRepository intakeTakenRepository;
	
	@Autowired
	public MedicationRPCServiceImpl(MedicationPlanService medicationPlanService,
									IntakeIntervalRepository intakeIntervalRepository,
									IntakeTakenRepository intakeTakenRepository) {
		this.medicationPlanService = medicationPlanService;
		this.intakeIntervalRepository = intakeIntervalRepository;
		this.intakeTakenRepository = intakeTakenRepository;
	}
	
	@Override
	public void notifyMedicationTaken(MedicationTakenMessage request, StreamObserver<Empty> responseObserver) {
		IntakeInterval intakeReferenced = intakeIntervalRepository.getOne(request.getIntakeIntervalId());
		IntakeTaken intakeTaken = IntakeTaken.builder()
				.intakeInterval(intakeReferenced)
				.date(LocalDate.now())
				.taken(true) // FIXME: should account for messages that it was NOT taken, too!
				.build();
		intakeTakenRepository.save(intakeTaken);
		
		responseObserver.onNext(Empty.newBuilder().build());
		responseObserver.onCompleted();
	}

	@Override
	public void getPlansForPatient(MedicationPlanRequest request,
								   StreamObserver<MedicationPlanResponse> responseObserver) {
		
		List<MedicationPlanNoPatientDTO> patientMedicationPlans = medicationPlanService
				.findAllNoPatientByPatientIdForToday(request.getPatientId());
		
		responseObserver.onNext(MedicationPlanResponse.newBuilder()
				.addAllMedicationPlan(patientMedicationPlans.stream()
						.map(MedicationRPCServiceImpl::dtoToRpc)
						.collect(Collectors.toList()))
				.build());
		
		
		responseObserver.onCompleted();
	}
	
	private static MedicationPlanRPCDTO dtoToRpc(MedicationPlanNoPatientDTO dto) {
		return MedicationPlanRPCDTO.newBuilder()
				.setPlanId(dto.getId())
				.addAllInterval(dto.getIntakeIntervals().stream()
						.map(MedicationRPCServiceImpl::dtoToRpc)
						.collect(Collectors.toList()))
				.build();
	}
	
	private static IntakeIntervalRPCDTO dtoToRpc(IntakeIntervalDTO dto) {
		return IntakeIntervalRPCDTO.newBuilder()
				.setIntervalId(dto.getId())
				.setStartTime(dto.getStartTime())
				.setEndTime(dto.getEndTime())
				.setMedication(dtoToRpc(dto.getMedication()))
				.build();
	}
	
	private static MedicationRPCDTO dtoToRpc(MedicationDTO dto) {
		return MedicationRPCDTO.newBuilder()
				.setMedicationId(dto.getId())
				.setName(dto.getName())
				.setDosage(dto.getDosage())
				.setSideEffects(dto.getSideEffects())
				.build();
	}
}
